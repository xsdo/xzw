package cn.zealon.readingcloud.homepage.service.impl;

import cn.zealon.readingcloud.account.feign.client.TableClient;
import cn.zealon.readingcloud.common.pojo.xzwtasks.ActiveTasklog;
import cn.zealon.readingcloud.homepage.dao.ActiveTasklogDao;
import cn.zealon.readingcloud.homepage.service.ActiveTasklogService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 活动用户记录表(ActiveTasklog)表服务实现类
 *
 * @author makejava
 * @since 2023-03-24 13:46:37
 */
@Service("activeTasklogService")
public class ActiveTasklogServiceImpl implements ActiveTasklogService {
    @Resource
    private ActiveTasklogDao activeTasklogDao;

    @Autowired
    private TableClient tableClient;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ActiveTasklog queryById(Long id) {
        return this.activeTasklogDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param activeTasklog 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    @Override
    public Page<ActiveTasklog> queryByPage(ActiveTasklog activeTasklog, PageRequest pageRequest) {
        long total = this.activeTasklogDao.count(activeTasklog);
        return new PageImpl<>(this.activeTasklogDao.queryAllByLimit(activeTasklog, pageRequest), pageRequest, total);
    }

    @Override
    public List<ActiveTasklog> queryAll(ActiveTasklog activeTasklog){
        return this.activeTasklogDao.queryAll(activeTasklog);
    }
    @Override
    public Integer checkEnter(Long userId,Long taskId){
        ActiveTasklog activeTasklog=new ActiveTasklog();
        activeTasklog.setIsused(0);
        activeTasklog.setUserId(userId);
        activeTasklog.setTaskId(taskId);
        List<ActiveTasklog> activeTasklogList = this.activeTasklogDao.queryAll(activeTasklog);
        if (activeTasklogList!=null&&activeTasklogList.size() > 0){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public JSONObject toEnter(Long userId , Long taskId ){
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try {
            //查询是否报名
            ActiveTasklog activeTasklog=new ActiveTasklog();
            activeTasklog.setIsused(0);
            activeTasklog.setUserId(userId);
            activeTasklog.setTaskId(taskId);
            List<ActiveTasklog> activeTasklogList = this.activeTasklogDao.queryAll(activeTasklog);
            if (activeTasklogList!=null&&activeTasklogList.size() > 0){
                result.put("sign",00);
                data.put("data","您已经报名");
            }else {
                activeTasklog.setCreateTime(new Date());
                activeTasklog.setUpdateTime(new Date());
                activeTasklog.setStatus(1);
                this.activeTasklogDao.insert(activeTasklog);

                if (taskId==1){
                    //参与活动1送标签10
                    Long tableId=new Long(10);
                    tableClient.toTableAdd(userId, tableId);
                }
                result.put("sign",00);
                data.put("data","报名成功");

            }
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
     * @param activeTasklog 实例对象
     * @return 实例对象
     */
    @Override
    public ActiveTasklog insert(ActiveTasklog activeTasklog) {
        this.activeTasklogDao.insert(activeTasklog);
        return activeTasklog;
    }

    /**
     * 修改数据
     *
     * @param activeTasklog 实例对象
     * @return 实例对象
     */
    @Override
    public ActiveTasklog update(ActiveTasklog activeTasklog) {
        this.activeTasklogDao.update(activeTasklog);
        return this.queryById(activeTasklog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.activeTasklogDao.deleteById(id) > 0;
    }
}
