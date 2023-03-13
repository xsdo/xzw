package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.CSchool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 校园作文表(CSchool)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 11:13:57
 */
public interface CSchoolService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CSchool queryById(Long id);

    /**
     * 分页查询
     *
     * @param cSchool     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<CSchool> queryByPage(CSchool cSchool, PageRequest pageRequest);

    List<CSchool> queryAll(CSchool cSchool);
    /**
     * 新增数据
     *
     * @param cSchool 实例对象
     * @return 实例对象
     */
    CSchool insert(CSchool cSchool);

    /**
     * 修改数据
     *
     * @param cSchool 实例对象
     * @return 实例对象
     */
    CSchool update(CSchool cSchool);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
