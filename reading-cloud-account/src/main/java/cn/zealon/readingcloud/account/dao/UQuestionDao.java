package cn.zealon.readingcloud.account.dao;

import cn.zealon.readingcloud.common.pojo.xzwusers.UQuestion;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 问题反馈表(UQuestion)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-10 17:54:34
 */
public interface UQuestionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UQuestion queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param uQuestion 查询条件
     * @param pageable  分页对象
     * @return 对象列表
     */
    List<UQuestion> queryAllByLimit(UQuestion uQuestion, @Param("pageable") Pageable pageable);
    List<UQuestion> queryAll(UQuestion uQuestion);

    /**
     * 统计总行数
     *
     * @param uQuestion 查询条件
     * @return 总行数
     */
    long count(UQuestion uQuestion);

    /**
     * 新增数据
     *
     * @param uQuestion 实例对象
     * @return 影响行数
     */
    int insert(UQuestion uQuestion);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UQuestion> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UQuestion> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UQuestion> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<UQuestion> entities);

    /**
     * 修改数据
     *
     * @param uQuestion 实例对象
     * @return 影响行数
     */
    int update(UQuestion uQuestion);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

