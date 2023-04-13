package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.TeacherTasks;
import cn.zealon.readingcloud.book.service.TeacherTasksService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 教师任务表(TeacherTasks)表控制层
 *
 * @author makejava
 * @since 2023-04-10 15:47:50
 */
@Api(description = "教师任务接口")
@RestController
@RequestMapping("book/teacherTasks")
public class TeacherTasksController {
    /**
     * 服务对象
     */
    @Resource
    private TeacherTasksService teacherTasksService;

    /**
     * 分页查询
     *
     * @param teacherTasks 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<TeacherTasks>> queryByPage(TeacherTasks teacherTasks, PageRequest pageRequest) {
        return ResponseEntity.ok(this.teacherTasksService.queryByPage(teacherTasks, pageRequest));
    }

    @GetMapping("addTask")
    public List<TeacherTasks> addTask(Long userId, Long compositionId, int type, String taskTime) {
        return this.teacherTasksService.addTask(userId, compositionId, type, taskTime);
    }

    @GetMapping("pushTask")
    public void pushTask() {
        this.teacherTasksService.pushTask();
    }

    @GetMapping("queryTask")
    public List<TeacherTasks> queryTask(Long userId, String taskTime) {
        return this.teacherTasksService.queryTask(userId, taskTime);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<TeacherTasks> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.teacherTasksService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param teacherTasks 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<TeacherTasks> add(TeacherTasks teacherTasks) {
        return ResponseEntity.ok(this.teacherTasksService.insert(teacherTasks));
    }

    /**
     * 编辑数据
     *
     * @param teacherTasks 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<TeacherTasks> edit(TeacherTasks teacherTasks) {
        return ResponseEntity.ok(this.teacherTasksService.update(teacherTasks));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.teacherTasksService.deleteById(id));
    }

}

