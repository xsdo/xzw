package cn.zealon.readingcloud.homepage.service;

import cn.zealon.readingcloud.common.pojo.xzwtasks.ActiveTask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 活动任务表(ActiveTask)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 11:11:41
 */
public interface ActiveTaskService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ActiveTask queryById(Long id);

    /**
     * 分页查询
     *
     * @param activeTask  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<ActiveTask> queryByPage(ActiveTask activeTask, PageRequest pageRequest);

    List<ActiveTask> queryAll (ActiveTask activeTask);
    /**
     * 新增数据
     *
     * @param activeTask 实例对象
     * @return 实例对象
     */
    ActiveTask insert(ActiveTask activeTask);

    /**
     * 修改数据
     *
     * @param activeTask 实例对象
     * @return 实例对象
     */
    ActiveTask update(ActiveTask activeTask);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
