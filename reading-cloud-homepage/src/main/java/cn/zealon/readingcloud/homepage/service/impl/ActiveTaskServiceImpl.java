package cn.zealon.readingcloud.homepage.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwtasks.ActiveTask;
import cn.zealon.readingcloud.homepage.dao.ActiveTaskDao;
import cn.zealon.readingcloud.homepage.service.ActiveTaskService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 活动任务表(ActiveTask)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:11:41
 */
@Service("activeTaskService")
public class ActiveTaskServiceImpl implements ActiveTaskService {
    @Resource
    private ActiveTaskDao activeTaskDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ActiveTask queryById(Long id) {
        return this.activeTaskDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param activeTask  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<ActiveTask> queryByPage(ActiveTask activeTask, PageRequest pageRequest) {
        long total = this.activeTaskDao.count(activeTask);
        return new PageImpl<>(this.activeTaskDao.queryAllByLimit(activeTask, pageRequest), pageRequest, total);
    }

    @Override
    public List<ActiveTask>queryAll (ActiveTask activeTask){
        return this.activeTaskDao.queryAll(activeTask);
    }
    /**
     * 新增数据
     *
     * @param activeTask 实例对象
     * @return 实例对象
     */
    @Override
    public ActiveTask insert(ActiveTask activeTask) {
        this.activeTaskDao.insert(activeTask);
        return activeTask;
    }

    /**
     * 修改数据
     *
     * @param activeTask 实例对象
     * @return 实例对象
     */
    @Override
    public ActiveTask update(ActiveTask activeTask) {
        this.activeTaskDao.update(activeTask);
        return this.queryById(activeTask.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.activeTaskDao.deleteById(id) > 0;
    }
}
