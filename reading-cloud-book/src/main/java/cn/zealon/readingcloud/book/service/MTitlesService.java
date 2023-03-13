package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.MTitles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 杂志目录表(MTitles)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 11:13:54
 */
public interface MTitlesService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MTitles queryById(Long id);

    /**
     * 分页查询
     *
     * @param mTitles     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<MTitles> queryByPage(MTitles mTitles, PageRequest pageRequest);

    List<MTitles> queryAll(MTitles mTitles);
    /**
     * 新增数据
     *
     * @param mTitles 实例对象
     * @return 实例对象
     */
    MTitles insert(MTitles mTitles);

    /**
     * 修改数据
     *
     * @param mTitles 实例对象
     * @return 实例对象
     */
    MTitles update(MTitles mTitles);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
