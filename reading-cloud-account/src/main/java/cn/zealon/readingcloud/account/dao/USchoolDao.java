package cn.zealon.readingcloud.account.dao;

import cn.zealon.readingcloud.common.pojo.xzwusers.USchool;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户学校表(USchool)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-06 09:23:22
 */
public interface USchoolDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    USchool queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param uSchool  查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<USchool> queryAllByLimit(USchool uSchool, @Param("pageable") Pageable pageable);
    List<USchool> queryAll(USchool uSchool);

    /**
     * 统计总行数
     *
     * @param uSchool 查询条件
     * @return 总行数
     */
    long count(USchool uSchool);

    /**
     * 新增数据
     *
     * @param uSchool 实例对象
     * @return 影响行数
     */
    int insert(USchool uSchool);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<USchool> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<USchool> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<USchool> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<USchool> entities);

    /**
     * 修改数据
     *
     * @param uSchool 实例对象
     * @return 影响行数
     */
    int update(USchool uSchool);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

