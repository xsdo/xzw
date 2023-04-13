package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.book.vo.StatisticsVO;
import cn.zealon.readingcloud.book.vo.StudentTasksVO;
import cn.zealon.readingcloud.book.vo.StudentVO;
import cn.zealon.readingcloud.common.pojo.xzwresources.StudentTasks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 学生任务表(StudentTasks)表服务接口
 *
 * @author makejava
 * @since 2023-04-10 15:47:48
 */
public interface StudentTasksService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    StudentTasks queryById(Long id);

    /**
     * 分页查询
     *
     * @param studentTasks 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    Page<StudentTasks> queryByPage(StudentTasks studentTasks, PageRequest pageRequest);

    void puskTask();


    StatisticsVO queryStatistics(Long teacherId , String taskTime);

    List<StudentVO> queryAllByTime(Long teacherId , String taskTime);

    List<StudentTasks> queryByUserId(Long userId);

    void updateByUserId(Long userId);

    void updateStatus(Long id);
    /**
     * 新增数据
     *
     * @param studentTasks 实例对象
     * @return 实例对象
     */
    StudentTasks insert(StudentTasks studentTasks);

    /**
     * 修改数据
     *
     * @param studentTasks 实例对象
     * @return 实例对象
     */
    StudentTasks update(StudentTasks studentTasks);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
