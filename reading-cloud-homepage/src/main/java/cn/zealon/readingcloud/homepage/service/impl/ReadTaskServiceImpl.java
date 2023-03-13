package cn.zealon.readingcloud.homepage.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwtasks.ReadTask;
import cn.zealon.readingcloud.homepage.dao.ReadTaskDao;
import cn.zealon.readingcloud.homepage.service.ReadTaskService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 阅读任务表(ReadTask)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:11:42
 */
@Service("readTaskService")
public class ReadTaskServiceImpl implements ReadTaskService {
    @Resource
    private ReadTaskDao readTaskDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ReadTask queryById(Long id) {
        return this.readTaskDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param readTask    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<ReadTask> queryByPage(ReadTask readTask, PageRequest pageRequest) {
        long total = this.readTaskDao.count(readTask);
        return new PageImpl<>(this.readTaskDao.queryAllByLimit(readTask, pageRequest), pageRequest, total);
    }

    @Override
    public List<ReadTask> queryAll(ReadTask readTask) {
        return this.readTaskDao.queryAll(readTask);
    }

    @Override
    public ReadTask queryOne(){
        ReadTask readTask=new ReadTask();
        readTask.setIsused(0);
        List<ReadTask> list = this.readTaskDao.queryAll(readTask);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }else {
            return null;
        }
    }


    /**
     * 新增数据
     *
     * @param readTask 实例对象
     * @return 实例对象
     */
    @Override
    public ReadTask insert(ReadTask readTask) {
        this.readTaskDao.insert(readTask);
        return readTask;
    }

    /**
     * 修改数据
     *
     * @param readTask 实例对象
     * @return 实例对象
     */
    @Override
    public ReadTask update(ReadTask readTask) {
        this.readTaskDao.update(readTask);
        return this.queryById(readTask.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.readTaskDao.deleteById(id) > 0;
    }
}
