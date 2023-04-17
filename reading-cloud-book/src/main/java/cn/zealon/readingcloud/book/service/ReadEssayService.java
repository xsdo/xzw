package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.ReadEssay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 阅读文章表(ReadEssay)表服务接口
 *
 * @author makejava
 * @since 2023-04-17 09:28:15
 */
public interface ReadEssayService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ReadEssay queryById(Long id);

    /**
     * 分页查询
     *
     * @param readEssay   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<ReadEssay> queryByPage(ReadEssay readEssay, PageRequest pageRequest);

    List<ReadEssay> queryAll(ReadEssay readEssay);
    /**
     * 新增数据
     *
     * @param readEssay 实例对象
     * @return 实例对象
     */
    ReadEssay insert(ReadEssay readEssay);

    /**
     * 修改数据
     *
     * @param readEssay 实例对象
     * @return 实例对象
     */
    ReadEssay update(ReadEssay readEssay);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
