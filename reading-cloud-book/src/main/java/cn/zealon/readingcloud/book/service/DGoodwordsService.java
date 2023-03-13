package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.DGoodwords;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 部编好词表(DGoodwords)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 11:13:59
 */
public interface DGoodwordsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DGoodwords queryById(Long id);

    /**
     * 分页查询
     *
     * @param dGoodwords  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<DGoodwords> queryByPage(DGoodwords dGoodwords, PageRequest pageRequest);

    List<DGoodwords> queryAll(DGoodwords dGoodwords);
    /**
     * 新增数据
     *
     * @param dGoodwords 实例对象
     * @return 实例对象
     */
    DGoodwords insert(DGoodwords dGoodwords);

    /**
     * 修改数据
     *
     * @param dGoodwords 实例对象
     * @return 实例对象
     */
    DGoodwords update(DGoodwords dGoodwords);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
