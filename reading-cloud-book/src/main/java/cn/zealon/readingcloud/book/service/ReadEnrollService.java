package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.ReadEnroll;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 活动报名表(ReadEnroll)表服务接口
 *
 * @author makejava
 * @since 2023-04-17 09:28:19
 */
public interface ReadEnrollService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ReadEnroll queryById(Long id);

    /**
     * 分页查询
     *
     * @param readEnroll  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<ReadEnroll> queryByPage(ReadEnroll readEnroll, PageRequest pageRequest);

    List<ReadEnroll> queryAll(ReadEnroll readEnroll);
    /**
     * 新增数据
     *
     * @param readEnroll 实例对象
     * @return 实例对象
     */
    ReadEnroll insert(ReadEnroll readEnroll);

    /**
     * 修改数据
     *
     * @param readEnroll 实例对象
     * @return 实例对象
     */
    ReadEnroll update(ReadEnroll readEnroll);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
