package cn.zealon.readingcloud.account.service;

import cn.zealon.readingcloud.common.pojo.xzwusers.UFans;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 用户粉丝表(UFans)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 10:43:54
 */
public interface UFansService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UFans queryById(Long id);

    /**
     * 分页查询
     *
     * @param uFans       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<UFans> queryByPage(UFans uFans, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param uFans 实例对象
     * @return 实例对象
     */
    UFans insert(UFans uFans);

    /**
     * 修改数据
     *
     * @param uFans 实例对象
     * @return 实例对象
     */
    UFans update(UFans uFans);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
