package cn.zealon.readingcloud.book.dao;

import cn.zealon.readingcloud.common.pojo.xzwresources.TeacherTasks;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 教师任务表(TeacherTasks)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-10 15:47:50
 */
public interface TeacherTasksDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TeacherTasks queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param teacherTasks 查询条件
     * @param pageable     分页对象
     * @return 对象列表
     */
    List<TeacherTasks> queryAllByLimit(TeacherTasks teacherTasks, @Param("pageable") Pageable pageable);

    List<TeacherTasks> queryAll(TeacherTasks teacherTasks);

    /**
     * 统计总行数
     *
     * @param teacherTasks 查询条件
     * @return 总行数
     */
    long count(TeacherTasks teacherTasks);

    /**
     * 新增数据
     *
     * @param teacherTasks 实例对象
     * @return 影响行数
     */
    int insert(TeacherTasks teacherTasks);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TeacherTasks> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TeacherTasks> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TeacherTasks> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<TeacherTasks> entities);

    /**
     * 修改数据
     *
     * @param teacherTasks 实例对象
     * @return 影响行数
     */
    int update(TeacherTasks teacherTasks);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

