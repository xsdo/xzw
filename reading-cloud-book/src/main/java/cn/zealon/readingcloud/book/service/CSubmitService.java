package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.CSubmit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 投稿表(CSubmit)表服务接口
 *
 * @author makejava
 * @since 2023-03-10 17:51:35
 */
public interface CSubmitService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CSubmit queryById(Long id);

    /**
     * 分页查询
     *
     * @param cSubmit     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<CSubmit> queryByPage(CSubmit cSubmit, PageRequest pageRequest);

    List<CSubmit> queryAll(CSubmit cSubmit);
    /**
     * 新增数据
     *
     * @param cSubmit 实例对象
     * @return 实例对象
     */
    CSubmit insert(CSubmit cSubmit);

    /**
     * 修改数据
     *
     * @param cSubmit 实例对象
     * @return 实例对象
     */
    CSubmit update(CSubmit cSubmit);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
