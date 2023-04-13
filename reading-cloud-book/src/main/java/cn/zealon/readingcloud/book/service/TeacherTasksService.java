package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.TeacherTasks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

/**
 * 教师任务表(TeacherTasks)表服务接口
 *
 * @author makejava
 * @since 2023-04-10 15:47:51
 */
public interface TeacherTasksService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TeacherTasks queryById(Long id);

    /**
     * 分页查询
     *
     * @param teacherTasks 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    Page<TeacherTasks> queryByPage(TeacherTasks teacherTasks, PageRequest pageRequest);

    List<TeacherTasks> addTask(Long userId, Long compositionId,int type, String taskTime);

    List<TeacherTasks> queryTask(Long userId, String taskTime);

    List<TeacherTasks> queryTaskByTeacherId(Long teacherId, String taskTime);

    void pushTask();
    /**
     * 查询所有数据
     *
     * @param teacherTasks 筛选条件
     * @return 对象列表
     */
    List<TeacherTasks> queryAll(TeacherTasks teacherTasks);
    /**
     * 新增数据
     *
     * @param teacherTasks 实例对象
     * @return 实例对象
     */
    TeacherTasks insert(TeacherTasks teacherTasks);

    /**
     * 修改数据
     *
     * @param teacherTasks 实例对象
     * @return 实例对象
     */
    TeacherTasks update(TeacherTasks teacherTasks);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
