package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.Magazines;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 杂志表(Magazines)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 11:13:55
 */
public interface MagazinesService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Magazines queryById(Long id);

    /**
     * 分页查询
     *
     * @param magazines   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Magazines> queryByPage(Magazines magazines, PageRequest pageRequest);

    List<Magazines> queryAll(Magazines magazines);
    /**
     * 新增数据
     *
     * @param magazines 实例对象
     * @return 实例对象
     */
    Magazines insert(Magazines magazines);

    /**
     * 修改数据
     *
     * @param magazines 实例对象
     * @return 实例对象
     */
    Magazines update(Magazines magazines);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
