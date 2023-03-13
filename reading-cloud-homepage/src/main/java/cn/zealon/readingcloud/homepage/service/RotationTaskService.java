package cn.zealon.readingcloud.homepage.service;

import cn.zealon.readingcloud.common.pojo.xzwtasks.RotationTask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 轮播图(RotationTask)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 11:11:41
 */
public interface RotationTaskService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RotationTask queryById(Long id);

    /**
     * 分页查询
     *
     * @param rotationTask 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    Page<RotationTask> queryByPage(RotationTask rotationTask, PageRequest pageRequest);

    List<RotationTask> queryAll(RotationTask rotationTask);

    /**
     * 新增数据
     *
     * @param rotationTask 实例对象
     * @return 实例对象
     */
    RotationTask insert(RotationTask rotationTask);

    /**
     * 修改数据
     *
     * @param rotationTask 实例对象
     * @return 实例对象
     */
    RotationTask update(RotationTask rotationTask);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
