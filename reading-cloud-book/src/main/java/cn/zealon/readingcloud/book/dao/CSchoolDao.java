package cn.zealon.readingcloud.book.dao;

import cn.zealon.readingcloud.common.pojo.xzwresources.CSchool;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 校园作文表(CSchool)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-01 11:13:57
 */
public interface CSchoolDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CSchool queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param cSchool  查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<CSchool> queryAllByLimit(CSchool cSchool, @Param("pageable") Pageable pageable);
    List<CSchool> queryAll(CSchool cSchool);

    /**
     * 统计总行数
     *
     * @param cSchool 查询条件
     * @return 总行数
     */
    long count(CSchool cSchool);

    /**
     * 新增数据
     *
     * @param cSchool 实例对象
     * @return 影响行数
     */
    int insert(CSchool cSchool);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<CSchool> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<CSchool> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<CSchool> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<CSchool> entities);

    /**
     * 修改数据
     *
     * @param cSchool 实例对象
     * @return 影响行数
     */
    int update(CSchool cSchool);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

