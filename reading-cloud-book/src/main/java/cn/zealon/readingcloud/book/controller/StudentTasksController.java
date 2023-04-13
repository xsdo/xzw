package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.book.service.StudentTasksService;
import cn.zealon.readingcloud.book.vo.StatisticsVO;
import cn.zealon.readingcloud.book.vo.StudentTasksVO;
import cn.zealon.readingcloud.book.vo.StudentVO;
import cn.zealon.readingcloud.common.pojo.xzwresources.StudentTasks;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 学生任务表(StudentTasks)表控制层
 *
 * @author makejava
 * @since 2023-04-10 15:47:46
 */
@Api(description = "学生任务接口")
@RestController
@RequestMapping("book/studentTasks")
public class StudentTasksController {
    /**
     * 服务对象
     */
    @Resource
    private StudentTasksService studentTasksService;

    /**
     * 分页查询
     *
     * @param studentTasks 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<StudentTasks>> queryByPage(StudentTasks studentTasks, PageRequest pageRequest) {
        return ResponseEntity.ok(this.studentTasksService.queryByPage(studentTasks, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
//    @GetMapping("{id}")
    public ResponseEntity<StudentTasks> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.studentTasksService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param studentTasks 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<StudentTasks> add(StudentTasks studentTasks) {
        return ResponseEntity.ok(this.studentTasksService.insert(studentTasks));
    }

    @GetMapping("puskTask")
    public void puskTask() {
        this.studentTasksService.puskTask();
    }

    @GetMapping("queryStatistics")
    public StatisticsVO queryStatistics(Long teacherId , String taskTime) {
        return this.studentTasksService.queryStatistics(teacherId, taskTime);
    }
    @GetMapping("queryAllByTime")
    public List<StudentVO> queryAllByTime(Long teacherId , String taskTime) {
        return this.studentTasksService.queryAllByTime(teacherId, taskTime);
    }

    @GetMapping("queryByUserId")
    public List<StudentTasks> queryByUserId(Long userId) {
        return this.studentTasksService.queryByUserId(userId);
    }

    @GetMapping("updateByUserId")
    public void updateByUserId(Long userId) {
        this.studentTasksService.updateByUserId(userId);
    }

    @GetMapping("updateStatus")
    public void updateStatus(Long id) {
        this.studentTasksService.updateStatus(id);
    }
    /**
     * 编辑数据
     *
     * @param studentTasks 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<StudentTasks> edit(StudentTasks studentTasks) {
        return ResponseEntity.ok(this.studentTasksService.update(studentTasks));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.studentTasksService.deleteById(id));
    }

}

