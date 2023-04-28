package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.account.service.UAttributeService;
import cn.zealon.readingcloud.account.service.VipProductService;
import cn.zealon.readingcloud.common.pojo.xzwusers.UAttribute;
import cn.zealon.readingcloud.common.pojo.xzwusers.UserOrder;
import cn.zealon.readingcloud.account.dao.UserOrderDao;
import cn.zealon.readingcloud.account.service.UserOrderService;
import cn.zealon.readingcloud.common.pojo.xzwusers.VipProduct;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单表(UserOrder)表服务实现类
 *
 * @author makejava
 * @since 2023-04-26 17:38:03
 */
@Service("userOrderService")
public class UserOrderServiceImpl implements UserOrderService {
    @Resource
    private UserOrderDao userOrderDao;


    @Resource
    private VipProductService vipProductService;

    @Resource
    private UAttributeService uAttributeService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserOrder queryById(Long id) {
        return this.userOrderDao.queryById(id);
    }

    @Override
    public List<UserOrder> queryAll(UserOrder userOrder) {
        return this.userOrderDao.queryAll(userOrder);
    }

    @Override
    public UserOrder queryByOrderNode(String orderNo){
        UserOrder userOrder=new UserOrder();
        userOrder.setIsused(0);
        userOrder.setOrderNo(orderNo);
        List<UserOrder> userOrderList =this.userOrderDao.queryAll(userOrder);
        if (userOrderList.size() > 0) {
            return userOrderList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public JSONObject createOrder(Long userId , Long productId){
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try{
            //查询用户
            UAttribute user=this.uAttributeService.queryById(userId);
            if (user == null) {
                result.put("sign",-1);
                result.put("data","用户不存在");
                return result;
            }
            //查询是否有未完成订单
            UserOrder userOrder =new UserOrder();
            userOrder.setIsused(0);
            userOrder.setUserId(userId);
            userOrder.setStatus(1);
            List<UserOrder> userOrderList =this.userOrderDao.queryAll(userOrder);
            if (userOrderList.size() > 0) {
                result.put("sign",-1);
                result.put("data","您有未付款订单");
                return result;
            }else {
                VipProduct vipProduct=this.vipProductService.queryById(productId);
                if (vipProduct != null) {
                    UserOrder newUserOrder =new UserOrder();
                    newUserOrder.setIsused(0);
                    newUserOrder.setCreateTime(new Date());
                    newUserOrder.setUpdateTime(new Date());
                    //随机生成订单号
                    String orderNo=IdWorker.getIdStr();
                    newUserOrder.setOrderNo(orderNo);
                    newUserOrder.setUserId(userId);
                    //判断是否活动促销
                    if (vipProduct.getPreferential()==1) {
                        newUserOrder.setPayment(vipProduct.getDisPrice());
                    }else {
                        newUserOrder.setPayment(vipProduct.getPrice());
                    }
                    newUserOrder.setPaymentType(1);
                    newUserOrder.setPostage(0);
                    newUserOrder.setStatus(1);
                    newUserOrder.setName(vipProduct.getName());
                    this.insert(newUserOrder);
                    result.put("sign",00);
                    result.put("data","订单创建成功");
                    result.put("orderNo",orderNo);
                }else {
                    result.put("sign",-1);
                    result.put("data","该商品不存在");
                    return result;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            result.put("sign",-1);
            result.put("data","服务器错误，请重试");
        }
        return result;
    }


    /**
     * 分页查询
     *
     * @param userOrder   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<UserOrder> queryByPage(UserOrder userOrder, PageRequest pageRequest) {
        long total = this.userOrderDao.count(userOrder);
        return new PageImpl<>(this.userOrderDao.queryAllByLimit(userOrder, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param userOrder 实例对象
     * @return 实例对象
     */
    @Override
    public UserOrder insert(UserOrder userOrder) {
        this.userOrderDao.insert(userOrder);
        return userOrder;
    }

    /**
     * 修改数据
     *
     * @param userOrder 实例对象
     * @return 实例对象
     */
    @Override
    public UserOrder update(UserOrder userOrder) {
        this.userOrderDao.update(userOrder);
        return this.queryById(userOrder.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userOrderDao.deleteById(id) > 0;
    }
}
