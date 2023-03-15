package cn.zealon.readingcloud.homepage.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwtasks.AuthTasklog;
import cn.zealon.readingcloud.common.pojo.xzwtasks.ReadTask;
import cn.zealon.readingcloud.common.pojo.xzwtasks.ReadTasklog;
import cn.zealon.readingcloud.homepage.dao.ReadTasklogDao;
import cn.zealon.readingcloud.homepage.service.AuthTasklogService;
import cn.zealon.readingcloud.homepage.service.ReadTaskService;
import cn.zealon.readingcloud.homepage.service.ReadTasklogService;
import com.alibaba.fastjson.JSONObject;
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
 * 阅读任务记录表(ReadTasklog)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:11:40
 */
@Service("readTasklogService")
public class ReadTasklogServiceImpl implements ReadTasklogService {
    @Resource
    private ReadTasklogDao readTasklogDao;

    @Resource
    private ReadTaskService readTaskService;

    @Resource
    private AuthTasklogService authTasklogService;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ReadTasklog queryById(Long id) {
        return this.readTasklogDao.queryById(id);
    }

    @Override
    public List<ReadTasklog>queryAll(ReadTasklog readTasklog){
        return this.readTasklogDao.queryAll(readTasklog);
    }
    /**
     * 分页查询
     *
     * @param readTasklog 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<ReadTasklog> queryByPage(ReadTasklog readTasklog, PageRequest pageRequest) {
        long total = this.readTasklogDao.count(readTasklog);
        return new PageImpl<>(this.readTasklogDao.queryAllByLimit(readTasklog, pageRequest), pageRequest, total);
    }

    @Override
    public JSONObject doReadTasklog(Long readTasklogId,int taskNum){
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try {
            ReadTasklog readTasklog = this.readTasklogDao.queryById(readTasklogId);
            if (readTasklog!=null){
                if (taskNum==1){
                    readTasklog.setStatusfirst(2);
                    this.readTasklogDao.update(readTasklog);
                }else if (taskNum == 2) {
                    readTasklog.setStatussecond(2);
                    this.readTasklogDao.update(readTasklog);
                }
                result.put("sign",00);
                data.put("data","任务完成");
            }
            ReadTasklog readTasklog1=this.readTasklogDao.queryById(readTasklogId);
            if (readTasklog1 != null) {
                if (readTasklog1.getStatusfirst()==2&&readTasklog1.getStatussecond()==2){
                    //redis
                    this.authTasklogService.setRedisTask(readTasklog1.getUserId(),new Long(3));
                    result.put("sign",00);
                    data.put("data","任务已完成");
                }
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
    public List<ReadTasklog>queryAllByUserId(Long userId){
        ReadTask readTask=new ReadTask();
        readTask.setIsused(0);
        readTask.setRStatus(1);
        List<ReadTask>readTaskList=this.readTaskService.queryAll(readTask);
        if (readTaskList.size() > 0) {
            for (int i = 0; i <readTaskList.size(); i++){
                checkReadTask(readTaskList.get(i).getId(),userId);
            }
        }
        ReadTasklog readTasklog=new ReadTasklog();
        readTasklog.setIsused(0);
        readTasklog.setUserId(userId);
        return this.readTasklogDao.queryAll(readTasklog);
    }


    public  void checkReadTask(Long readTaskId,Long userId){
        ReadTasklog readTasklog=new ReadTasklog();
        readTasklog.setIsused(0);
        readTasklog.setUserId(userId);
        readTasklog.setTaskId(readTaskId);
        List<ReadTasklog>readTasklogList=this.queryAll(readTasklog);
        if (readTasklogList==null||readTasklogList.size() == 0){
            ReadTasklog r=new ReadTasklog();
            r.setIsused(0);
            r.setUserId(userId);
            r.setCreateTime(new Date());
            r.setUpdateTime(new Date());
            r.setTaskId(readTaskId);
            r.setStatusfirst(0);
            r.setStatussecond(0);
            this.insert(r);
        }
    }
    /**
     * 新增数据
     *
     * @param readTasklog 实例对象
     * @return 实例对象
     */
    @Override
    public ReadTasklog insert(ReadTasklog readTasklog) {
        this.readTasklogDao.insert(readTasklog);
        return readTasklog;
    }

    /**
     * 修改数据
     *
     * @param readTasklog 实例对象
     * @return 实例对象
     */
    @Override
    public ReadTasklog update(ReadTasklog readTasklog) {
        this.readTasklogDao.update(readTasklog);
        return this.queryById(readTasklog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.readTasklogDao.deleteById(id) > 0;
    }
}
