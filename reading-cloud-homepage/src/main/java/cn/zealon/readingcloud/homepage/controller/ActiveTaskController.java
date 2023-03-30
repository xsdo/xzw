package cn.zealon.readingcloud.homepage.controller;

import cn.zealon.readingcloud.common.pojo.xzwtasks.ActiveTask;
import cn.zealon.readingcloud.homepage.service.ActiveTaskService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 活动任务表(ActiveTask)表控制层
 *
 * @author makejava
 * @since 2023-03-01 11:11:40
 */
@Api(description = "活动任务表")
@RestController
@RequestMapping("index/activeTask")
public class ActiveTaskController {
    /**
     * 服务对象
     */
    @Resource
    private ActiveTaskService activeTaskService;

    /**
     * 分页查询
     *
     * @param activeTask  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<ActiveTask>> queryByPage(ActiveTask activeTask, PageRequest pageRequest) {
        return ResponseEntity.ok(this.activeTaskService.queryByPage(activeTask, pageRequest));
    }

    @GetMapping("queryAll")
    public List<ActiveTask>queryAll(ActiveTask activeTask){
        return this.activeTaskService.queryAll(activeTask);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ActiveTask> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.activeTaskService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param activeTask 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<ActiveTask> add(ActiveTask activeTask) {
        return ResponseEntity.ok(this.activeTaskService.insert(activeTask));
    }

    /**
     * 编辑数据
     *
     * @param activeTask 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<ActiveTask> edit(ActiveTask activeTask) {
        return ResponseEntity.ok(this.activeTaskService.update(activeTask));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.activeTaskService.deleteById(id));
    }

}

