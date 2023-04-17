package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.ReadBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 阅读图书表(ReadBook)表服务接口
 *
 * @author makejava
 * @since 2023-04-17 09:28:16
 */
public interface ReadBookService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ReadBook queryById(Long id);

    /**
     * 分页查询
     *
     * @param readBook    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<ReadBook> queryByPage(ReadBook readBook, PageRequest pageRequest);

    List<ReadBook> queryAll(ReadBook readBook);
    /**
     * 新增数据
     *
     * @param readBook 实例对象
     * @return 实例对象
     */
    ReadBook insert(ReadBook readBook);

    /**
     * 修改数据
     *
     * @param readBook 实例对象
     * @return 实例对象
     */
    ReadBook update(ReadBook readBook);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
