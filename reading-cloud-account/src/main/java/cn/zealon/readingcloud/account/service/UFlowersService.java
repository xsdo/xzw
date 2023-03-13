package cn.zealon.readingcloud.account.service;

import cn.zealon.readingcloud.common.pojo.xzwusers.UFlowers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 鲜花表(UFlowers)表服务接口
 *
 * @author makejava
 * @since 2023-03-06 09:23:23
 */
public interface UFlowersService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UFlowers queryById(Long id);

    /**
     * 分页查询
     *
     * @param uFlowers    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<UFlowers> queryByPage(UFlowers uFlowers, PageRequest pageRequest);

    List<UFlowers> queryAll(UFlowers uFlowers);

    List<UFlowers>queryByUserId(Long userId);

    List<UFlowers>queryByTeacherId(Long teacherId);

    /**
     * 新增数据
     *
     * @param uFlowers 实例对象
     * @return 实例对象
     */
    UFlowers insert(UFlowers uFlowers);

    /**
     * 修改数据
     *
     * @param uFlowers 实例对象
     * @return 实例对象
     */
    UFlowers update(UFlowers uFlowers);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
