package cn.zealon.readingcloud.homepage.controller;

import cn.zealon.readingcloud.common.pojo.xzwtasks.ReadTasklog;
import cn.zealon.readingcloud.homepage.service.ReadTasklogService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 阅读任务记录表(ReadTasklog)表控制层
 *
 * @author makejava
 * @since 2023-03-01 11:11:40
 */
@Api(description = "阅读任务记录")
@RestController
@RequestMapping("index/readTasklog")
public class ReadTasklogController {
    /**
     * 服务对象
     */
    @Resource
    private ReadTasklogService readTasklogService;

    /**
     * 分页查询
     *
     * @param readTasklog 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<ReadTasklog>> queryByPage(ReadTasklog readTasklog, PageRequest pageRequest) {
        return ResponseEntity.ok(this.readTasklogService.queryByPage(readTasklog, pageRequest));
    }

    @GetMapping("queryAllByUserId")
    public List<ReadTasklog>queryAllByUserId(Long userId){
        return this.readTasklogService.queryAllByUserId(userId);
    }

    @GetMapping("doReadTask")
    public JSONObject doReadTasklog(Long readTasklogId, int taskNum){
        return this.readTasklogService.doReadTasklog(readTasklogId,taskNum);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ReadTasklog> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.readTasklogService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param readTasklog 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<ReadTasklog> add(ReadTasklog readTasklog) {
        return ResponseEntity.ok(this.readTasklogService.insert(readTasklog));
    }

    /**
     * 编辑数据
     *
     * @param readTasklog 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<ReadTasklog> edit(ReadTasklog readTasklog) {
        return ResponseEntity.ok(this.readTasklogService.update(readTasklog));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.readTasklogService.deleteById(id));
    }

}

