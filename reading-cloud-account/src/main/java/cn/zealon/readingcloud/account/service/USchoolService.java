package cn.zealon.readingcloud.account.service;

import cn.zealon.readingcloud.common.pojo.xzwusers.USchool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 用户学校表(USchool)表服务接口
 *
 * @author makejava
 * @since 2023-03-06 09:23:22
 */
public interface USchoolService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    USchool queryById(Long id);

    /**
     * 分页查询
     *
     * @param uSchool     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<USchool> queryByPage(USchool uSchool, PageRequest pageRequest);

    List<USchool> queryAll(USchool uSchool);
    /**
     * 新增数据
     *
     * @param uSchool 实例对象
     * @return 实例对象
     */
    USchool insert(USchool uSchool);

    /**
     * 修改数据
     *
     * @param uSchool 实例对象
     * @return 实例对象
     */
    USchool update(USchool uSchool);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
