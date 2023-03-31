package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.account.service.UAttributeService;
import cn.zealon.readingcloud.account.service.UTeacherService;
import cn.zealon.readingcloud.common.cache.RedisService;
import cn.zealon.readingcloud.common.pojo.xzwusers.UAttribute;
import cn.zealon.readingcloud.common.pojo.xzwusers.UFlowers;
import cn.zealon.readingcloud.account.dao.UFlowersDao;
import cn.zealon.readingcloud.account.service.UFlowersService;
import cn.zealon.readingcloud.common.pojo.xzwusers.UTeacher;
import com.alibaba.fastjson.JSONObject;
import netscape.javascript.JSObject;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.zealon.readingcloud.common.cache.RedisExpire.DAY;

/**
 * 鲜花表(UFlowers)表服务实现类
 *
 * @author makejava
 * @since 2023-03-06 09:23:23
 */
@Service("uFlowersService")
public class UFlowersServiceImpl implements UFlowersService {
    @Resource
    private UFlowersDao uFlowersDao;

    @Resource
    private UAttributeService uAttributeService;

    @Resource
    private RedisService redisService;

    @Resource
    private UTeacherService uTeacherService;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UFlowers queryById(Long id) {
        return this.uFlowersDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param uFlowers    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<UFlowers> queryByPage(UFlowers uFlowers, PageRequest pageRequest) {
        long total = this.uFlowersDao.count(uFlowers);
        return new PageImpl<>(this.uFlowersDao.queryAllByLimit(uFlowers, pageRequest), pageRequest, total);
    }

    @Override
    public List<UFlowers>queryAll(UFlowers uFlowers){
        return this.uFlowersDao.queryAll(uFlowers);
    }

    @Override
    public List<UFlowers>queryByUserId(Long userId){
        UFlowers uFlowers=new UFlowers();
        uFlowers.setIsused(0);
        uFlowers.setUserId(userId);
        return this.uFlowersDao.queryAll(uFlowers);
    }

    @Override
    public List<UFlowers>queryByTeacherId(Long teacherId) {
        UFlowers uFlowers = new UFlowers();
        uFlowers.setIsused(0);
        uFlowers.setTeacherId(teacherId);
        return this.uFlowersDao.queryAll(uFlowers);
    }

    public int queryTodayFlowers(Long userId,Long teacherId){
        int flowers=0;
        List<UFlowers> uFlowersList = this.uFlowersDao.queryToday();
        if (!uFlowersList.isEmpty()){
            for (UFlowers uFlowers:uFlowersList) {
                if (uFlowers.getUserId().equals(userId)&&uFlowers.getTeacherId().equals(teacherId)){
                    flowers-=uFlowers.getFlowers();
                }
             }
        }
        return flowers;
    }
    @Override
    public JSONObject giveFlowers(Long userId,Long teacherId, int flowers){
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try{
            UAttribute uAttribute=uAttributeService.queryById(userId);
            UTeacher uTeacher =uTeacherService.queryById(teacherId);
            if (uAttribute != null&&uTeacher!=null){
                int flowersToday=this.queryTodayFlowers(userId,teacherId);
                if (flowersToday+flowers>5){
                    result.put("sign", -1);
                    data.put("data", "每日最多可捐献5积分");
                } else if (uAttribute.getIntegral()<flowers) {
                    result.put("sign", -1);
                    data.put("data", "积分不足");
                }else if (!teacherId .equals(uAttribute.getTeacherid()) ){
                    result.put("sign", -1);
                    data.put("data", "只允许捐献自己所属班级");
                }else {
                    //添加积分明细
                    UFlowers uFlowers =new UFlowers();
                    uFlowers.setIsused(0);
                    uFlowers.setUserId(userId);
                    uFlowers.setFlowers(-flowers);
                    uFlowers.setTeacherId(teacherId);
                    uFlowers.setCreateTime(new Date());
                    uFlowers.setUpdateTime(new Date());
                    uFlowers.setRemarks("班级捐赠");
                    this.uFlowersDao.insert(uFlowers);

                    //捐赠成功个人-分
                    uAttribute.setIntegral(uAttribute.getIntegral()-flowers);
                    uAttribute.setUpdateTime(new Date());
                    uAttributeService.update(uAttribute);
                    //班级+分
                    uTeacher.setTFlowers(uTeacher.getTFlowers() + flowers);
                    uTeacher.setUpdateTime(new Date());
                    uTeacherService.update(uTeacher);
                    //捐赠成功 缓存到redis 班级任务id 2
                    this.setRedisTask(userId,new Long(2));

                    result.put("sign", 00);
                    data.put("data", "捐赠成功");
                }
            }else {
                result.put("sign", -1);
                data.put("data", "用户信息查询失败");
            }
            result.put("data",data);
        }catch (Exception e) {
            e.printStackTrace();
            result.put("sign",-1);
            result.put("data","服务器错误");
        }
        return result;
    }

    @Override
    public JSONObject addFlowers(Long userId, int flowers, String remarks){
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try{
            //添加积分明细
            UFlowers uFlowers =new UFlowers();
            uFlowers.setIsused(0);
            uFlowers.setUserId(userId);
            uFlowers.setFlowers(flowers);
            uFlowers.setCreateTime(new Date());
            uFlowers.setUpdateTime(new Date());
            uFlowers.setRemarks(remarks);
            this.uFlowersDao.insert(uFlowers);

            //给用户属性添加积分
            UAttribute uAttribute=uAttributeService.queryById(userId);
            if (uAttribute != null){
                //签到成功+分
                uAttribute.setIntegral(uAttribute.getIntegral()+flowers);
                uAttribute.setUpdateTime(new Date());
                uAttributeService.update(uAttribute);
            }
            result.put("sign", 00);
            data.put("data", "积分添加成功");
            result.put("data",data);
        }catch (Exception e) {
            e.printStackTrace();
            result.put("sign",-1);
            result.put("data","服务器错误");
        }
        return result;
    }
    /**
     * 新增数据
     *
     * @param uFlowers 实例对象
     * @return 实例对象
     */
    @Override
    public UFlowers insert(UFlowers uFlowers) {
        this.uFlowersDao.insert(uFlowers);
        return uFlowers;
    }

    public  void setRedisTask(Long userId,Long taskId){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String format = simpleDateFormat.format(date);
        String key = format+":user"+userId+":task"+taskId;
        String taskCount=redisService.getCache(key);
        if (taskCount==null){
            redisService.setExpireCache(key,1,DAY);
        }else {
            redisService.setExpireCache(key,taskCount+1,DAY);
        }
    }
    /**
     * 修改数据
     *
     * @param uFlowers 实例对象
     * @return 实例对象
     */
    @Override
    public UFlowers update(UFlowers uFlowers) {
        this.uFlowersDao.update(uFlowers);
        return this.queryById(uFlowers.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.uFlowersDao.deleteById(id) > 0;
    }
}
