package cn.zealon.readingcloud.account.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.zealon.readingcloud.account.service.UVipService;
import cn.zealon.readingcloud.common.pojo.xzwusers.UActivation;
import cn.zealon.readingcloud.account.dao.UActivationDao;
import cn.zealon.readingcloud.account.service.UActivationService;
import cn.zealon.readingcloud.common.pojo.xzwusers.UVip;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 激活码表(UActivation)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 10:43:52
 */
@Service("uActivationService")
public class UActivationServiceImpl implements UActivationService {
    @Resource
    private UActivationDao uActivationDao;

    @Resource
    private UVipService uVipService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UActivation queryById(Long id) {
        return this.uActivationDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param uActivation 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<UActivation> queryByPage(UActivation uActivation, PageRequest pageRequest) {
        long total = this.uActivationDao.count(uActivation);
        return new PageImpl<>(this.uActivationDao.queryAllByLimit(uActivation, pageRequest), pageRequest, total);
    }

    @Override
    public List<UActivation>queryAll(UActivation uActivation){
        return this.uActivationDao.queryAll(uActivation);
    }

    @Override //随机生成激活码
    public JSONObject createActivationCode(String createUser, Long vipType, int sum,int month){
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try {
            if (sum>1){
                for(int i = 0; i <sum;i++){
                    UActivation uActivation = new UActivation();
                    uActivation.setIsused(0);
                    uActivation.setCreateTime(new Date());
                    uActivation.setUpdateTime(new Date());
                    uActivation.setVipType(vipType);
                    uActivation.setVCreatetime(new Date());
                    uActivation.setVEndtime(this.getMonthDate(new Date(),month));
                    uActivation.setStatus(0);
                    uActivation.setCreateUser(createUser);
                    String code= IdUtil.fastSimpleUUID();
                    uActivation.setActivationCode(code);
                    this.uActivationDao.insert(uActivation);
                }
            }else if (sum==1){
                UActivation uActivation = new UActivation();
                uActivation.setIsused(0);
                uActivation.setCreateTime(new Date());
                uActivation.setUpdateTime(new Date());
                uActivation.setVipType(vipType);
                uActivation.setVCreatetime(new Date());
                uActivation.setVEndtime(this.getMonthDate(new Date(),month));
                uActivation.setStatus(0);
                uActivation.setCreateUser(createUser);
                String code= IdUtil.fastSimpleUUID();
                uActivation.setActivationCode(code);
                this.uActivationDao.insert(uActivation);
            }
            result.put("sign",00);
            data.put("data","激活码创建成功");
        }catch (Exception e) {
            e.printStackTrace();
            result.put("sign",-1);
            result.put("data","服务器错误");
        }
        result.put("data",data);
        return result;
    }
    @Override
    public JSONObject useActivationCode(Long userId,String activationCode){
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try{
            UActivation uActivation=new UActivation();
            uActivation.setActivationCode(activationCode);
            uActivation.setIsused(0);
            List<UActivation>uActivationList=this.uActivationDao.queryAll(uActivation);
            if (uActivationList.size() > 0) {
                UActivation u=uActivationList.get(0);
                if (u != null) {
                    if (u.getStatus()==1){
                        result.put("sign",-1);
                        data.put("data","激活码已被使用");
                    }else if (u.getVEndtime().before(new Date())){
                        result.put("sign",-1);
                        data.put("data","激活码已过期");
                    }else if (u.getStatus() == 0&&u.getVEndtime().after(new Date())) {
                        if (u.getVipType()==1){//终身会员
                            uVipService.toVipByCode(userId,5,600,activationCode);
                            u.setStatus(1);
                            u.setVUsetime(new Date());
                            u.setUserId(userId);
                            this.uActivationDao.update(u);
                        }else if (u.getVipType() == 2) {//年卡
                            uVipService.toVipByCode(userId,4,12,activationCode);
                            u.setStatus(1);
                            u.setVUsetime(new Date());
                            u.setUserId(userId);
                            this.uActivationDao.update(u);
                        }
                        result.put("sign",00);
                        data.put("data","激活码使用成功");
                    }
                }else {
                    result.put("sign",-1);
                    data.put("data","无效激活码");
                }
            }else {
                result.put("sign",-1);
                data.put("data","无效激活码");
            }
        }catch (Exception e) {
            e.printStackTrace();
            result.put("sign",-1);
            result.put("data","服务器错误");
        }
        result.put("data",data);
        return result;
    }

    public  Date getMonthDate(Date startDate,int month){
        LocalDateTime localDateTime = startDate.toInstant()
                .atZone(ZoneId.systemDefault() )
                .toLocalDateTime().plusMonths(month);
        Date date = Date.from(localDateTime.atZone( ZoneId.systemDefault()).toInstant());
        return date;
    }
    /**
     * 新增数据
     *
     * @param uActivation 实例对象
     * @return 实例对象
     */
    @Override
    public UActivation insert(UActivation uActivation) {
        this.uActivationDao.insert(uActivation);
        return uActivation;
    }

    /**
     * 修改数据
     *
     * @param uActivation 实例对象
     * @return 实例对象
     */
    @Override
    public UActivation update(UActivation uActivation) {
        this.uActivationDao.update(uActivation);
        return this.queryById(uActivation.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.uActivationDao.deleteById(id) > 0;
    }
}
