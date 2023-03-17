package cn.zealon.readingcloud.homepage.service;

import cn.zealon.readingcloud.common.pojo.xzwtasks.ReadTask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 阅读任务表(ReadTask)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 11:11:42
 */
public interface ReadTaskService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ReadTask queryById(Long id);

    /**
     * 分页查询
     *
     * @param readTask    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<ReadTask> queryByPage(ReadTask readTask, PageRequest pageRequest);

    List<ReadTask> queryAll(ReadTask readTask);

    ReadTask queryOne();

    void toReadTask();

    /**
     * 新增数据
     *
     * @param readTask 实例对象
     * @return 实例对象
     */
    ReadTask insert(ReadTask readTask);

    /**
     * 修改数据
     *
     * @param readTask 实例对象
     * @return 实例对象
     */
    ReadTask update(ReadTask readTask);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
