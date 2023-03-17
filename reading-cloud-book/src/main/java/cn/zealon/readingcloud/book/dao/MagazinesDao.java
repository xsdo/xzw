package cn.zealon.readingcloud.book.dao;

import cn.zealon.readingcloud.common.pojo.xzwresources.Magazines;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 杂志栏目表(Magazines)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-15 17:17:09
 */
public interface MagazinesDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Magazines queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param magazines 查询条件
     * @param pageable  分页对象
     * @return 对象列表
     */
    List<Magazines> queryAllByLimit(Magazines magazines, @Param("pageable") Pageable pageable);
    List<Magazines> queryAll(Magazines magazines);

    /**
     * 统计总行数
     *
     * @param magazines 查询条件
     * @return 总行数
     */
    long count(Magazines magazines);

    /**
     * 新增数据
     *
     * @param magazines 实例对象
     * @return 影响行数
     */
    int insert(Magazines magazines);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Magazines> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Magazines> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Magazines> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Magazines> entities);

    /**
     * 修改数据
     *
     * @param magazines 实例对象
     * @return 影响行数
     */
    int update(Magazines magazines);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

