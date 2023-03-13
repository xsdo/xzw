package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.CRecitation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 朗读者表(CRecitation)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 11:13:57
 */
public interface CRecitationService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CRecitation queryById(Long id);

    /**
     * 分页查询
     *
     * @param cRecitation 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<CRecitation> queryByPage(CRecitation cRecitation, PageRequest pageRequest);

    List<CRecitation> queryAll(CRecitation cRecitation);

    /**
     * 新增数据
     *
     * @param cRecitation 实例对象
     * @return 实例对象
     */
    CRecitation insert(CRecitation cRecitation);

    /**
     * 修改数据
     *
     * @param cRecitation 实例对象
     * @return 实例对象
     */
    CRecitation update(CRecitation cRecitation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
