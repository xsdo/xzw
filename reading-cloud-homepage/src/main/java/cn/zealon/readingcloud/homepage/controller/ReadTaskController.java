package cn.zealon.readingcloud.homepage.controller;

import cn.zealon.readingcloud.common.pojo.xzwtasks.ReadTask;
import cn.zealon.readingcloud.homepage.service.ReadTaskService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

/**
 * 阅读任务表(ReadTask)表控制层
 *
 * @author makejava
 * @since 2023-03-01 11:11:42
 */
@Api(description = "阅读任务")
@RestController
@RequestMapping("index/readTask")
public class ReadTaskController {
    /**
     * 服务对象
     */
    @Resource
    private ReadTaskService readTaskService;

    /**
     * 分页查询
     *
     * @param readTask    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<ReadTask>> queryByPage(ReadTask readTask, PageRequest pageRequest) {
        return ok(this.readTaskService.queryByPage(readTask, pageRequest));
    }

    @GetMapping("queryAll")
    public List<ReadTask> queryAll(ReadTask readTask){
        return this.readTaskService.queryAll(readTask);
    }

    @GetMapping("queryOne")
    public ReadTask queryOne(){
        return this.readTaskService.queryOne();
    }


    @GetMapping("toReadTask")
    public void toReadTask(){
        this.readTaskService.toReadTask();
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ReadTask> queryById(@PathVariable("id") Long id) {
        return ok(this.readTaskService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param readTask 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<ReadTask> add(ReadTask readTask) {
        return ok(this.readTaskService.insert(readTask));
    }

    /**
     * 编辑数据
     *
     * @param readTask 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<ReadTask> edit(ReadTask readTask) {
        return ok(this.readTaskService.update(readTask));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ok(this.readTaskService.deleteById(id));
    }

}

