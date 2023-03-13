package cn.zealon.readingcloud.homepage.service;

import cn.zealon.readingcloud.common.pojo.xzwtasks.AuthTask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 认证任务表(AuthTask)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 11:11:42
 */
public interface AuthTaskService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthTask queryById(Long id);

    /**
     * 分页查询
     *
     * @param authTask    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<AuthTask> queryByPage(AuthTask authTask, PageRequest pageRequest);

    List<AuthTask> queryAll(AuthTask authTask);
    /**
     * 新增数据
     *
     * @param authTask 实例对象
     * @return 实例对象
     */
    AuthTask insert(AuthTask authTask);

    /**
     * 修改数据
     *
     * @param authTask 实例对象
     * @return 实例对象
     */
    AuthTask update(AuthTask authTask);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
