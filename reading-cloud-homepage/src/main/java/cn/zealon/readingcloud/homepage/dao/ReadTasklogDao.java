package cn.zealon.readingcloud.homepage.dao;

import cn.zealon.readingcloud.common.pojo.xzwtasks.ReadTasklog;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 阅读任务记录表(ReadTasklog)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-01 11:11:40
 */
public interface ReadTasklogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ReadTasklog queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param readTasklog 查询条件
     * @param pageable    分页对象
     * @return 对象列表
     */
    List<ReadTasklog> queryAllByLimit(ReadTasklog readTasklog, @Param("pageable") Pageable pageable);

    List<ReadTasklog> queryAll(ReadTasklog readTasklog);

    /**
     * 统计总行数
     *
     * @param readTasklog 查询条件
     * @return 总行数
     */
    long count(ReadTasklog readTasklog);

    /**
     * 新增数据
     *
     * @param readTasklog 实例对象
     * @return 影响行数
     */
    int insert(ReadTasklog readTasklog);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ReadTasklog> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ReadTasklog> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ReadTasklog> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ReadTasklog> entities);

    /**
     * 修改数据
     *
     * @param readTasklog 实例对象
     * @return 影响行数
     */
    int update(ReadTasklog readTasklog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

