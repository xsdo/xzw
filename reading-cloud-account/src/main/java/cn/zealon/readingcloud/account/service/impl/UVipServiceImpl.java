package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwusers.UVip;
import cn.zealon.readingcloud.account.dao.UVipDao;
import cn.zealon.readingcloud.account.service.UVipService;
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
 * 用户会员表(UVip)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 10:43:57
 */
@Service("uVipService")
public class UVipServiceImpl implements UVipService {
    @Resource
    private UVipDao uVipDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UVip queryById(Long id) {
        return this.uVipDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param uVip        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<UVip> queryByPage(UVip uVip, PageRequest pageRequest) {
        long total = this.uVipDao.count(uVip);
        return new PageImpl<>(this.uVipDao.queryAllByLimit(uVip, pageRequest), pageRequest, total);
    }

    @Override
    public List<UVip>queryAll(UVip uVip){
        return this.uVipDao.queryAll(uVip);
    }

    @Override
    public JSONObject queryVipByUserId(Long userId){
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try {
            UVip uVip= new UVip();
            uVip.setUserId(userId);
            List<UVip> uVipList=this.uVipDao.queryAll(uVip);
            if (uVipList!=null&&uVipList.size() > 0){
                Date dateNow = new Date();
                Date endDate=uVipList.get(0).getVEndtime();
                if (dateNow.after(endDate)){//会员过期
                    data.put("vip",uVipList.get(0));
                    data.put("vipUse",1);
                }else {
                    data.put("vip",uVipList.get(0));
                    data.put("vipUse",0);
                }
                result.put("sign",00);
                result.put("data",data);
            }else {
                result.put("sign",-1);
                result.put("data","未发现会员数据");
            }
        }catch (Exception e) {
            e.printStackTrace();
            result.put("sign",-1);
            result.put("data","服务器错误");
        }
        return result;
    }

    @Override
    public void toVipFirst(Long userId){
        UVip u= new UVip();
        u.setUserId(userId);
        List<UVip> uVipList=this.uVipDao.queryAll(u);
        if (uVipList==null || uVipList.size() == 0){
            UVip uVip =new UVip();
            uVip.setIsused(0);
            uVip.setUserId(userId);
            uVip.setCreateTime(new Date());
            uVip.setUpdateTime(new Date());
            uVip.setVipType(1);
            uVip.setVBegintime(new Date());
            uVip.setVEndtime(this.getMonthDate(new Date(),1));
            this.insert(uVip);
        }
    }

    @Override
    public JSONObject toVip(Long userId,int vipType,int month){
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try{
            UVip u= new UVip();
            u.setUserId(userId);
            List<UVip> uVipList=this.uVipDao.queryAll(u);
            if (uVipList!=null&&uVipList.size() > 0){
                UVip uVip=uVipList.get(0);
                Date dateNow = new Date();
                Date endDate=uVip.getVEndtime();
                if (dateNow.after(endDate)){//会员过期
                    uVip.setVBegintime(new Date());
                    uVip.setVEndtime(this.getMonthDate(new Date(),month));
                    uVip.setVipType(vipType);
                    uVipDao.update(uVip);
                    data.put("vip",uVip);
                }else {
                    uVip.setVEndtime(this.getMonthDate(uVip.getVEndtime(),month));
                    uVip.setVipType(vipType);
                    uVipDao.update(uVip);
                    data.put("vip",uVip);
                }
            }
            result.put("sign",00);
            data.put("data","续费成功");
        }catch (Exception e) {
            e.printStackTrace();
            result.put("sign",-1);
            result.put("data","服务器错误");
        }
        result.put("data",data);
        return result;
    }
    @Override
    public JSONObject toVipByCode(Long userId,int vipType,int month,String code){
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try{
            UVip u= new UVip();
            u.setUserId(userId);
            List<UVip> uVipList=this.uVipDao.queryAll(u);
            if (uVipList!=null&&uVipList.size() > 0){
                UVip uVip=uVipList.get(0);
                Date dateNow = new Date();
                Date endDate=uVip.getVEndtime();
                if (dateNow.after(endDate)){//会员过期
                    uVip.setVBegintime(new Date());
                    uVip.setVEndtime(this.getMonthDate(new Date(),month));
                    uVip.setVipType(vipType);
                    uVip.setActivationCode(code);
                    uVipDao.update(uVip);
                    data.put("vip",uVip);
                }else {
                    uVip.setVEndtime(this.getMonthDate(uVip.getVEndtime(),month));
                    uVip.setVipType(vipType);
                    uVip.setActivationCode(code);
                    uVipDao.update(uVip);
                    data.put("vip",uVip);
                }
            }
            result.put("sign",00);
            data.put("data","续费成功");
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
     * @param uVip 实例对象
     * @return 实例对象
     */
    @Override
    public UVip insert(UVip uVip) {
        this.uVipDao.insert(uVip);
        return uVip;
    }

    /**
     * 修改数据
     *
     * @param uVip 实例对象
     * @return 实例对象
     */
    @Override
    public UVip update(UVip uVip) {
        this.uVipDao.update(uVip);
        return this.queryById(uVip.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.uVipDao.deleteById(id) > 0;
    }
}
