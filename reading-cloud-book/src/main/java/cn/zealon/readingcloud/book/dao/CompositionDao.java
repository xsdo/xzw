package cn.zealon.readingcloud.book.dao;

import cn.zealon.readingcloud.common.pojo.xzwresources.Composition;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 作文表(Composition)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-01 11:14:01
 */
public interface CompositionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Composition queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param composition 查询条件
     * @param pageable    分页对象
     * @return 对象列表
     */
    List<Composition> queryAllByLimit(Composition composition, @Param("pageable") Pageable pageable);
    List<Composition> queryAll(Composition composition);

    List<Composition>queryRand(int size);

    /**
     * 统计总行数
     *
     * @param composition 查询条件
     * @return 总行数
     */
    long count(Composition composition);

    /**
     * 新增数据
     *
     * @param composition 实例对象
     * @return 影响行数
     */
    int insert(Composition composition);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Composition> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Composition> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Composition> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Composition> entities);

    /**
     * 修改数据
     *
     * @param composition 实例对象
     * @return 影响行数
     */
    int update(Composition composition);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

