package cn.zealon.readingcloud.homepage.controller;

import cn.zealon.readingcloud.common.pojo.xzwtasks.SuggestTask;
import cn.zealon.readingcloud.homepage.service.SuggestTaskService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 推荐作文表(SuggestTask)表控制层
 *
 * @author makejava
 * @since 2023-03-01 11:11:43
 */
@Api(description = "推荐作文")
@RestController
@RequestMapping("index/suggestTask")
public class SuggestTaskController {
    /**
     * 服务对象
     */
    @Resource
    private SuggestTaskService suggestTaskService;

    /**
     * 分页查询
     *
     * @param suggestTask 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<SuggestTask>> queryByPage(SuggestTask suggestTask, PageRequest pageRequest) {
        return ResponseEntity.ok(this.suggestTaskService.queryByPage(suggestTask, pageRequest));
    }

    @GetMapping("/queryAll")
    public List<SuggestTask>queryAll(SuggestTask suggestTask){
        return this.suggestTaskService.queryAll(suggestTask);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<SuggestTask> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.suggestTaskService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param suggestTask 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<SuggestTask> add(SuggestTask suggestTask) {
        return ResponseEntity.ok(this.suggestTaskService.insert(suggestTask));
    }

    /**
     * 编辑数据
     *
     * @param suggestTask 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<SuggestTask> edit(SuggestTask suggestTask) {
        return ResponseEntity.ok(this.suggestTaskService.update(suggestTask));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.suggestTaskService.deleteById(id));
    }

}

