package cn.zealon.readingcloud.homepage.service.impl;

import cn.zealon.readingcloud.account.feign.client.FlowersClient;
import cn.zealon.readingcloud.common.cache.RedisService;
import cn.zealon.readingcloud.common.pojo.xzwtasks.AuthTask;
import cn.zealon.readingcloud.common.pojo.xzwtasks.AuthTasklog;
import cn.zealon.readingcloud.homepage.dao.AuthTasklogDao;
import cn.zealon.readingcloud.homepage.service.AuthTaskService;
import cn.zealon.readingcloud.homepage.service.AuthTasklogService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.zealon.readingcloud.common.cache.RedisExpire.DAY;

/**
 * 认证用户记录表(AuthTasklog)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:11:43
 */
@Service("authTasklogService")
public class AuthTasklogServiceImpl implements AuthTasklogService {
    @Resource
    private AuthTasklogDao authTasklogDao;
    @Resource
    private AuthTaskService authTaskService;
    @Resource
    private FlowersClient flowersClient;

    @Resource
    private RedisService redisService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AuthTasklog queryById(Long id) {
        return this.authTasklogDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param authTasklog 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<AuthTasklog> queryByPage(AuthTasklog authTasklog, PageRequest pageRequest) {
        long total = this.authTasklogDao.count(authTasklog);
        return new PageImpl<>(this.authTasklogDao.queryAllByLimit(authTasklog, pageRequest), pageRequest, total);
    }

    @Override
    public List<AuthTasklog>queryAll(AuthTasklog authTasklog){
        return this.authTasklogDao.queryAll(authTasklog);
    }

    @Override
    public void resetTaskLog(){
        AuthTasklog authTasklog = new AuthTasklog();
        authTasklog.setIsused(0);
        List<AuthTasklog>authTasklogList=this.queryAll(authTasklog);
        if (authTasklogList!=null){
            for (AuthTasklog aa:authTasklogList){
                aa.setStatus(0);
                this.update(aa);
            }
        }
    }
    @Override
    public List<AuthTasklog>queryByUserId(Long userId){
        AuthTask authTask =new AuthTask();
        authTask.setIsused(0);
        authTask.setAStatus(0);
        List<AuthTask>authTaskList=authTaskService.queryAll(authTask);
        if (authTaskList.size() > 0){
            for (int i = 0; i <authTaskList.size(); i++){
                checkLogByTask(authTaskList.get(i).getId(),userId);
            }
        }
        AuthTasklog authTasklog = new AuthTasklog();
        authTasklog.setUserId(userId);
        authTasklog.setIsused(0);
        List<AuthTasklog> authTasklogList = this.queryAll(authTasklog);
        if (!authTasklogList.isEmpty()) {
            for (AuthTasklog aa:authTasklogList){
                this.checkLogStatusByTask(aa.getId());
            }
        }
        authTasklogList = this.queryAll(authTasklog);
        return authTasklogList;
    }

    public void checkLogStatusByTask(Long authTasklogId){
        AuthTasklog authTasklog=this.queryById(authTasklogId);
        if (authTasklog != null) {
            if (authTasklog != null) {
                String count=this.getRedisTask(authTasklog.getUserId(),authTasklog.getTaskId());
                if (count != null){
                    if (authTasklog.getTaskId()==2||authTasklog.getTaskId() == 3||authTasklog.getTaskId() == 7){
                        if (count.length()>0){
                            authTasklog.setStatus(2);
                            this.update(authTasklog);
                        }
                    }else if (authTasklog.getTaskId() == 4 || authTasklog.getTaskId() == 5){
                        if (count.length()>1){
                            authTasklog.setStatus(2);
                            this.update(authTasklog);
                        }
                    }
                }
            }
        }

    }

    @Override//领取奖励
    public JSONObject toAuthTasklog(Long authTasklogId){
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try {
            AuthTasklog authTasklog=this.queryById(authTasklogId);
            if (authTasklog == null) {
                result.put("sign",-1);
                result.put("data","未找到该任务");
                return result;
            }
            AuthTask authTask=authTaskService.queryById(authTasklog.getTaskId());
            if (authTasklog != null) {
                if (authTasklog.getStatus() == 2) {
                    //给用户添加积分
                    flowersClient.addFlowers(authTasklog.getUserId(),Integer.parseInt(authTask.getAAward()),authTask.getAName());
                    //状态改为已领取奖励
                    authTasklog.setStatus(3);
                    this.update(authTasklog);
                    result.put("sign", 00);
                    data.put("data", "奖励领取成功");
                }else if (authTasklog.getStatus() == 3) {
                    result.put("sign", 00);
                    data.put("data", "奖励已领取");
                }
                result.put("data",data);
            }
        }catch (Exception e) {
            e.printStackTrace();
            result.put("sign",-1);
            result.put("data","服务器错误");
        }
        return result;
    }

    @Override
    public  String getRedisTask(Long userId,Long taskId){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String format = simpleDateFormat.format(date);
        String key = format+":user"+userId+":task"+taskId;
        String taskCount=redisService.getCache(key);
        return taskCount;
    }

    @Override
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
    public void checkLogByTask(Long taskId,Long userId){
        AuthTasklog au=new AuthTasklog();
        au.setIsused(0);
        au.setUserId(userId);
        au.setTaskId(taskId);
        List<AuthTasklog>authTaskslog=this.queryAll(au);
        if (authTaskslog==null||authTaskslog.size() == 0){
            AuthTasklog a=new AuthTasklog();
            a.setIsused(0);
            a.setUserId(userId);
            a.setCreateTime(new Date());
            a.setUpdateTime(new Date());
            a.setTaskId(taskId);
            a.setStatus(0);
            this.authTasklogDao.insert(a);
        }
    }
    /**
     * 新增数据
     *
     * @param authTasklog 实例对象
     * @return 实例对象
     */
    @Override
    public AuthTasklog insert(AuthTasklog authTasklog) {
        this.authTasklogDao.insert(authTasklog);
        return authTasklog;
    }

    /**
     * 修改数据
     *
     * @param authTasklog 实例对象
     * @return 实例对象
     */
    @Override
    public AuthTasklog update(AuthTasklog authTasklog) {
        this.authTasklogDao.update(authTasklog);
        return this.queryById(authTasklog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.authTasklogDao.deleteById(id) > 0;
    }
}
