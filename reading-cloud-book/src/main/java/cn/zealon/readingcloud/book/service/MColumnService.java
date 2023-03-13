package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.MColumn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 杂志栏目表(MColumn)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 11:14:00
 */
public interface MColumnService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MColumn queryById(Long id);

    /**
     * 分页查询
     *
     * @param mColumn     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<MColumn> queryByPage(MColumn mColumn, PageRequest pageRequest);

    List<MColumn> queryAll(MColumn mColumn);
    /**
     * 新增数据
     *
     * @param mColumn 实例对象
     * @return 实例对象
     */
    MColumn insert(MColumn mColumn);

    /**
     * 修改数据
     *
     * @param mColumn 实例对象
     * @return 实例对象
     */
    MColumn update(MColumn mColumn);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
