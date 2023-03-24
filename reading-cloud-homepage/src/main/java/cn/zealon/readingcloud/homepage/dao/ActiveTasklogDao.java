package cn.zealon.readingcloud.homepage.dao;

import cn.zealon.readingcloud.common.pojo.xzwtasks.ActiveTasklog;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 活动用户记录表(ActiveTasklog)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-24 13:46:37
 */
public interface ActiveTasklogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ActiveTasklog queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param activeTasklog 查询条件
     * @param pageable      分页对象
     * @return 对象列表
     */
    List<ActiveTasklog> queryAllByLimit(ActiveTasklog activeTasklog, @Param("pageable") Pageable pageable);
    List<ActiveTasklog> queryAll(ActiveTasklog activeTasklog);

    /**
     * 统计总行数
     *
     * @param activeTasklog 查询条件
     * @return 总行数
     */
    long count(ActiveTasklog activeTasklog);

    /**
     * 新增数据
     *
     * @param activeTasklog 实例对象
     * @return 影响行数
     */
    int insert(ActiveTasklog activeTasklog);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ActiveTasklog> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ActiveTasklog> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ActiveTasklog> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ActiveTasklog> entities);

    /**
     * 修改数据
     *
     * @param activeTasklog 实例对象
     * @return 影响行数
     */
    int update(ActiveTasklog activeTasklog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

