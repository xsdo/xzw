package cn.zealon.readingcloud.homepage.controller;

import cn.zealon.readingcloud.common.pojo.xzwtasks.RotationTask;
import cn.zealon.readingcloud.homepage.service.RotationTaskService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 轮播图(RotationTask)表控制层
 *
 * @author makejava
 * @since 2023-03-01 11:11:41
 */
@Api(description = "轮播图")
@RestController
@RequestMapping("index/rotationTask")
public class RotationTaskController {
    /**
     * 服务对象
     */
    @Resource
    private RotationTaskService rotationTaskService;

    /**
     * 分页查询
     *
     * @param rotationTask 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<RotationTask>> queryByPage(RotationTask rotationTask, PageRequest pageRequest) {
        return ResponseEntity.ok(this.rotationTaskService.queryByPage(rotationTask, pageRequest));
    }

    @GetMapping("queryAll")
    public ResponseEntity<List<RotationTask>> queryAll(RotationTask rotationTask) {
        return ResponseEntity.ok(this.rotationTaskService.queryAll(rotationTask));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<RotationTask> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.rotationTaskService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param rotationTask 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<RotationTask> add(RotationTask rotationTask) {
        return ResponseEntity.ok(this.rotationTaskService.insert(rotationTask));
    }

    /**
     * 编辑数据
     *
     * @param rotationTask 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<RotationTask> edit(RotationTask rotationTask) {
        return ResponseEntity.ok(this.rotationTaskService.update(rotationTask));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.rotationTaskService.deleteById(id));
    }

}

