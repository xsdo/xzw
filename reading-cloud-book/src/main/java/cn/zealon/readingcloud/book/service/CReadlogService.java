package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.CReadlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 阅读记录表(CReadlog)表服务接口
 *
 * @author makejava
 * @since 2023-03-10 17:51:35
 */
public interface CReadlogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CReadlog queryById(Long id);

    /**
     * 分页查询
     *
     * @param cReadlog    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<CReadlog> queryByPage(CReadlog cReadlog, PageRequest pageRequest);


    List<CReadlog> queryAll(CReadlog cReadlog);
    /**
     * 新增数据
     *
     * @param cReadlog 实例对象
     * @return 实例对象
     */
    CReadlog insert(CReadlog cReadlog);

    /**
     * 修改数据
     *
     * @param cReadlog 实例对象
     * @return 实例对象
     */
    CReadlog update(CReadlog cReadlog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
