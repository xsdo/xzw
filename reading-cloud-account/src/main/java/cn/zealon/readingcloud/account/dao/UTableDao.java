package cn.zealon.readingcloud.account.dao;

import cn.zealon.readingcloud.common.pojo.xzwusers.UTable;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户标签表(UTable)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-01 10:43:55
 */
public interface UTableDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UTable queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param uTable   查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<UTable> queryAllByLimit(UTable uTable, @Param("pageable") Pageable pageable);
    List<UTable> queryAll(UTable uTable);

    List<UTable> queryByIds(@Param("ids") ArrayList ids);
    /**
     * 统计总行数
     *
     * @param uTable 查询条件
     * @return 总行数
     */
    long count(UTable uTable);

    /**
     * 新增数据
     *
     * @param uTable 实例对象
     * @return 影响行数
     */
    int insert(UTable uTable);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UTable> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UTable> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UTable> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<UTable> entities);

    /**
     * 修改数据
     *
     * @param uTable 实例对象
     * @return 影响行数
     */
    int update(UTable uTable);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

