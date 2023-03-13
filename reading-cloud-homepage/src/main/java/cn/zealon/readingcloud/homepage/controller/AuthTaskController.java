package cn.zealon.readingcloud.homepage.controller;

import cn.zealon.readingcloud.common.pojo.xzwtasks.AuthTask;
import cn.zealon.readingcloud.homepage.service.AuthTaskService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 认证任务表(AuthTask)表控制层
 *
 * @author makejava
 * @since 2023-03-01 11:11:41
 */
@Api(description = "认证任务")
@RestController
@RequestMapping("index/authTask")
public class AuthTaskController {
    /**
     * 服务对象
     */
    @Resource
    private AuthTaskService authTaskService;

    /**
     * 分页查询
     *
     * @param authTask    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<AuthTask>> queryByPage(AuthTask authTask, PageRequest pageRequest) {
        return ResponseEntity.ok(this.authTaskService.queryByPage(authTask, pageRequest));
    }

    @GetMapping("queryAll")
    public List<AuthTask>queryAll(AuthTask authTask){
        return this.authTaskService.queryAll(authTask);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<AuthTask> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.authTaskService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param authTask 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<AuthTask> add(AuthTask authTask) {
        return ResponseEntity.ok(this.authTaskService.insert(authTask));
    }

    /**
     * 编辑数据
     *
     * @param authTask 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<AuthTask> edit(AuthTask authTask) {
        return ResponseEntity.ok(this.authTaskService.update(authTask));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.authTaskService.deleteById(id));
    }

}

