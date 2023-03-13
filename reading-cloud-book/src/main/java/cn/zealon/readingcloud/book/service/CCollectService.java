package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.CCollect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 收藏表(CCollect)表服务接口
 *
 * @author makejava
 * @since 2023-03-10 17:51:37
 */
public interface CCollectService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CCollect queryById(Long id);

    /**
     * 分页查询
     *
     * @param cCollect    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<CCollect> queryByPage(CCollect cCollect, PageRequest pageRequest);

    List<CCollect> queryAll(CCollect cCollect);
    /**
     * 新增数据
     *
     * @param cCollect 实例对象
     * @return 实例对象
     */
    CCollect insert(CCollect cCollect);

    /**
     * 修改数据
     *
     * @param cCollect 实例对象
     * @return 实例对象
     */
    CCollect update(CCollect cCollect);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
