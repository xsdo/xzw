package cn.zealon.readingcloud.book.dao;

import cn.zealon.readingcloud.common.pojo.xzwresources.Circle;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 圈子表(Circle)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-08 17:40:19
 */
public interface CircleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Circle queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param circle   查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Circle> queryAllByLimit(Circle circle, @Param("pageable") Pageable pageable);


    List<Circle> queryAll(Circle circle);

    /**
     * 统计总行数
     *
     * @param circle 查询条件
     * @return 总行数
     */
    long count(Circle circle);

    /**
     * 新增数据
     *
     * @param circle 实例对象
     * @return 影响行数
     */
    int insert(Circle circle);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Circle> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Circle> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Circle> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Circle> entities);

    /**
     * 修改数据
     *
     * @param circle 实例对象
     * @return 影响行数
     */
    int update(Circle circle);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

