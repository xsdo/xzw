package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.DGoodsentence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 部编好句表(DGoodsentence)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 11:13:53
 */
public interface DGoodsentenceService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DGoodsentence queryById(Long id);

    /**
     * 分页查询
     *
     * @param dGoodsentence 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    Page<DGoodsentence> queryByPage(DGoodsentence dGoodsentence, PageRequest pageRequest);

    List<DGoodsentence> queryAll(DGoodsentence dGoodsentence);
    /**
     * 新增数据
     *
     * @param dGoodsentence 实例对象
     * @return 实例对象
     */
    DGoodsentence insert(DGoodsentence dGoodsentence);

    /**
     * 修改数据
     *
     * @param dGoodsentence 实例对象
     * @return 实例对象
     */
    DGoodsentence update(DGoodsentence dGoodsentence);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
