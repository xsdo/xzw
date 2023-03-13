package cn.zealon.readingcloud.homepage.dao;

import cn.zealon.readingcloud.common.pojo.xzwtasks.SuggestTask;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 推荐作文表(SuggestTask)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-01 11:11:43
 */
public interface SuggestTaskDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SuggestTask queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param suggestTask 查询条件
     * @param pageable    分页对象
     * @return 对象列表
     */
    List<SuggestTask> queryAllByLimit(SuggestTask suggestTask, @Param("pageable") Pageable pageable);

    List<SuggestTask> queryAll(SuggestTask suggestTask);

    /**
     * 统计总行数
     *
     * @param suggestTask 查询条件
     * @return 总行数
     */
    long count(SuggestTask suggestTask);

    /**
     * 新增数据
     *
     * @param suggestTask 实例对象
     * @return 影响行数
     */
    int insert(SuggestTask suggestTask);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SuggestTask> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SuggestTask> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SuggestTask> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SuggestTask> entities);

    /**
     * 修改数据
     *
     * @param suggestTask 实例对象
     * @return 影响行数
     */
    int update(SuggestTask suggestTask);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

