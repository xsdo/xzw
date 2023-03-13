package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.MArticles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 杂志文章表(MArticles)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 11:13:59
 */
public interface MArticlesService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MArticles queryById(Long id);

    /**
     * 分页查询
     *
     * @param mArticles   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<MArticles> queryByPage(MArticles mArticles, PageRequest pageRequest);


    List<MArticles> queryAll(MArticles mArticles);
    /**
     * 新增数据
     *
     * @param mArticles 实例对象
     * @return 实例对象
     */
    MArticles insert(MArticles mArticles);

    /**
     * 修改数据
     *
     * @param mArticles 实例对象
     * @return 实例对象
     */
    MArticles update(MArticles mArticles);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
