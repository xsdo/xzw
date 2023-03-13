package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.CMuted;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 禁言表(CMuted)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 11:14:01
 */
public interface CMutedService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CMuted queryById(Long id);

    /**
     * 分页查询
     *
     * @param cMuted      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<CMuted> queryByPage(CMuted cMuted, PageRequest pageRequest);

    List<CMuted> queryAll(CMuted cMuted);
    /**
     * 新增数据
     *
     * @param cMuted 实例对象
     * @return 实例对象
     */
    CMuted insert(CMuted cMuted);

    /**
     * 修改数据
     *
     * @param cMuted 实例对象
     * @return 实例对象
     */
    CMuted update(CMuted cMuted);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
