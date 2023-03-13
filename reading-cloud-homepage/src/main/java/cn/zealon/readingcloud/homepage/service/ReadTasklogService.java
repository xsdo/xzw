package cn.zealon.readingcloud.homepage.service;

import cn.zealon.readingcloud.common.pojo.xzwtasks.ReadTasklog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 阅读任务记录表(ReadTasklog)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 11:11:40
 */
public interface ReadTasklogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ReadTasklog queryById(Long id);

    /**
     * 分页查询
     *
     * @param readTasklog 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<ReadTasklog> queryByPage(ReadTasklog readTasklog, PageRequest pageRequest);

    List<ReadTasklog> queryAllByUserId(Long userId);
    /**
     * 新增数据
     *
     * @param readTasklog 实例对象
     * @return 实例对象
     */
    ReadTasklog insert(ReadTasklog readTasklog);

    /**
     * 修改数据
     *
     * @param readTasklog 实例对象
     * @return 实例对象
     */
    ReadTasklog update(ReadTasklog readTasklog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
