package cn.zealon.readingcloud.book.dao;

import cn.zealon.readingcloud.common.pojo.xzwresources.ReadContest;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 活动参赛表(ReadContest)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-17 09:28:17
 */
public interface ReadContestDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ReadContest queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param readContest 查询条件
     * @param pageable    分页对象
     * @return 对象列表
     */
    List<ReadContest> queryAllByLimit(ReadContest readContest, @Param("pageable") Pageable pageable);
    List<ReadContest> queryAll(ReadContest readContest);

    /**
     * 统计总行数
     *
     * @param readContest 查询条件
     * @return 总行数
     */
    long count(ReadContest readContest);

    /**
     * 新增数据
     *
     * @param readContest 实例对象
     * @return 影响行数
     */
    int insert(ReadContest readContest);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ReadContest> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ReadContest> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ReadContest> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ReadContest> entities);

    /**
     * 修改数据
     *
     * @param readContest 实例对象
     * @return 影响行数
     */
    int update(ReadContest readContest);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

