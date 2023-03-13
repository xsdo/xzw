package cn.zealon.readingcloud.book.dao;

import cn.zealon.readingcloud.common.pojo.xzwresources.CNation;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 全国作文表(CNation)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-01 11:13:56
 */
public interface CNationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CNation queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param cNation  查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<CNation> queryAllByLimit(CNation cNation, @Param("pageable") Pageable pageable);
    List<CNation> queryAll(CNation cNation);

    /**
     * 统计总行数
     *
     * @param cNation 查询条件
     * @return 总行数
     */
    long count(CNation cNation);

    /**
     * 新增数据
     *
     * @param cNation 实例对象
     * @return 影响行数
     */
    int insert(CNation cNation);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<CNation> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<CNation> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<CNation> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<CNation> entities);

    /**
     * 修改数据
     *
     * @param cNation 实例对象
     * @return 影响行数
     */
    int update(CNation cNation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

