package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.CNation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 全国作文表(CNation)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 11:13:56
 */
public interface CNationService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CNation queryById(Long id);

    /**
     * 分页查询
     *
     * @param cNation     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<CNation> queryByPage(CNation cNation, PageRequest pageRequest);

    List<CNation> queryAll(CNation cNation);

    List<CNation> randNation(int size);

    /**
     * 新增数据
     *
     * @param cNation 实例对象
     * @return 实例对象
     */
    CNation insert(CNation cNation);

    /**
     * 修改数据
     *
     * @param cNation 实例对象
     * @return 实例对象
     */
    CNation update(CNation cNation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
