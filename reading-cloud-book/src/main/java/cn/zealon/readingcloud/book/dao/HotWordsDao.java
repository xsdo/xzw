package cn.zealon.readingcloud.book.dao;

import cn.zealon.readingcloud.common.pojo.xzwresources.HotWords;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 热搜表(HotWords)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-27 15:24:35
 */
public interface HotWordsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HotWords queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param hotWords 查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<HotWords> queryAllByLimit(HotWords hotWords, @Param("pageable") Pageable pageable);


    List<HotWords> queryAll(HotWords hotWords);
    List<HotWords> queryTop(int size);

    /**
     * 统计总行数
     *
     * @param hotWords 查询条件
     * @return 总行数
     */
    long count(HotWords hotWords);

    /**
     * 新增数据
     *
     * @param hotWords 实例对象
     * @return 影响行数
     */
    int insert(HotWords hotWords);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HotWords> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HotWords> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HotWords> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<HotWords> entities);

    /**
     * 修改数据
     *
     * @param hotWords 实例对象
     * @return 影响行数
     */
    int update(HotWords hotWords);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

