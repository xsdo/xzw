package cn.zealon.readingcloud.account.service;

import cn.zealon.readingcloud.common.pojo.xzwusers.UserOrder;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 订单表(UserOrder)表服务接口
 *
 * @author makejava
 * @since 2023-04-26 17:38:03
 */
public interface UserOrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserOrder queryById(Long id);

    UserOrder queryByOrderNode(String orderNo);

    List<UserOrder> queryAll(UserOrder userOrder);


    JSONObject createOrder(Long userId , Long productId);

    /**
     * 分页查询
     *
     * @param userOrder   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<UserOrder> queryByPage(UserOrder userOrder, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param userOrder 实例对象
     * @return 实例对象
     */
    UserOrder insert(UserOrder userOrder);

    /**
     * 修改数据
     *
     * @param userOrder 实例对象
     * @return 实例对象
     */
    UserOrder update(UserOrder userOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
