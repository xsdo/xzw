package cn.zealon.readingcloud.homepage.dao;

import cn.zealon.readingcloud.common.pojo.xzwtasks.ActiveTask;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 活动任务表(ActiveTask)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-01 11:11:40
 */
public interface ActiveTaskDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ActiveTask queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param activeTask 查询条件
     * @param pageable   分页对象
     * @return 对象列表
     */
    List<ActiveTask> queryAllByLimit(ActiveTask activeTask, @Param("pageable") Pageable pageable);
    List<ActiveTask> queryAll(ActiveTask activeTask);

    /**
     * 统计总行数
     *
     * @param activeTask 查询条件
     * @return 总行数
     */
    long count(ActiveTask activeTask);

    /**
     * 新增数据
     *
     * @param activeTask 实例对象
     * @return 影响行数
     */
    int insert(ActiveTask activeTask);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ActiveTask> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ActiveTask> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ActiveTask> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ActiveTask> entities);

    /**
     * 修改数据
     *
     * @param activeTask 实例对象
     * @return 影响行数
     */
    int update(ActiveTask activeTask);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

