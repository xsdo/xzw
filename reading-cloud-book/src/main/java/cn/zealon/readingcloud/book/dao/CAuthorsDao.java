package cn.zealon.readingcloud.book.dao;

import cn.zealon.readingcloud.common.pojo.xzwresources.CAuthors;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 作者表(CAuthors)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-01 11:14:02
 */
public interface CAuthorsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CAuthors queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param cAuthors 查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<CAuthors> queryAllByLimit(CAuthors cAuthors, @Param("pageable") Pageable pageable);
    List<CAuthors> queryAll(CAuthors cAuthors);

    /**
     * 统计总行数
     *
     * @param cAuthors 查询条件
     * @return 总行数
     */
    long count(CAuthors cAuthors);

    /**
     * 新增数据
     *
     * @param cAuthors 实例对象
     * @return 影响行数
     */
    int insert(CAuthors cAuthors);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<CAuthors> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<CAuthors> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<CAuthors> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<CAuthors> entities);

    /**
     * 修改数据
     *
     * @param cAuthors 实例对象
     * @return 影响行数
     */
    int update(CAuthors cAuthors);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

