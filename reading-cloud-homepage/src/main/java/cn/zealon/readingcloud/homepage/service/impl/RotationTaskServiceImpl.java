package cn.zealon.readingcloud.homepage.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwtasks.RotationTask;
import cn.zealon.readingcloud.homepage.dao.RotationTaskDao;
import cn.zealon.readingcloud.homepage.service.RotationTaskService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 轮播图(RotationTask)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:11:41
 */
@Service("rotationTaskService")
public class RotationTaskServiceImpl implements RotationTaskService {
    @Resource
    private RotationTaskDao rotationTaskDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public RotationTask queryById(Long id) {
        return this.rotationTaskDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param rotationTask 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    @Override
    public Page<RotationTask> queryByPage(RotationTask rotationTask, PageRequest pageRequest) {
        long total = this.rotationTaskDao.count(rotationTask);
        return new PageImpl<>(this.rotationTaskDao.queryAllByLimit(rotationTask, pageRequest), pageRequest, total);
    }

    @Override
    public List<RotationTask> queryAll(RotationTask rotationTask) {
        return (this.rotationTaskDao.queryAll(rotationTask));
    }


    /**
     * 新增数据
     *
     * @param rotationTask 实例对象
     * @return 实例对象
     */
    @Override
    public RotationTask insert(RotationTask rotationTask) {
        this.rotationTaskDao.insert(rotationTask);
        return rotationTask;
    }

    /**
     * 修改数据
     *
     * @param rotationTask 实例对象
     * @return 实例对象
     */
    @Override
    public RotationTask update(RotationTask rotationTask) {
        this.rotationTaskDao.update(rotationTask);
        return this.queryById(rotationTask.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.rotationTaskDao.deleteById(id) > 0;
    }
}
