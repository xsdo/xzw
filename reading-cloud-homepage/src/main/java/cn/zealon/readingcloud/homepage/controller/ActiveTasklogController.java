package cn.zealon.readingcloud.homepage.controller;

import cn.zealon.readingcloud.common.pojo.xzwtasks.ActiveTasklog;
import cn.zealon.readingcloud.homepage.service.ActiveTasklogService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 活动用户记录表(ActiveTasklog)表控制层
 *
 * @author makejava
 * @since 2023-03-24 13:46:37
 */
@Api(description = "活动任务记录")
@RestController
@RequestMapping("index/activeTasklog")
public class ActiveTasklogController {
    /**
     * 服务对象
     */
    @Resource
    private ActiveTasklogService activeTasklogService;

    /**
     * 分页查询
     *
     * @param activeTasklog 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<ActiveTasklog>> queryByPage(ActiveTasklog activeTasklog, PageRequest pageRequest) {
        return ResponseEntity.ok(this.activeTasklogService.queryByPage(activeTasklog, pageRequest));
    }


    @GetMapping("checkEnter")//查询是否报名
    public Integer checkEnter(Long userId,Long taskId){
        return this.activeTasklogService.checkEnter(userId, taskId);
    }

    @GetMapping("toEnter")//报名
    public JSONObject toEnter(Long userId , Long taskId ){
        return this.activeTasklogService.toEnter(userId, taskId);
    }



    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
//    @GetMapping("{id}")
    public ResponseEntity<ActiveTasklog> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.activeTasklogService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param activeTasklog 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<ActiveTasklog> add(ActiveTasklog activeTasklog) {
        return ResponseEntity.ok(this.activeTasklogService.insert(activeTasklog));
    }

    /**
     * 编辑数据
     *
     * @param activeTasklog 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<ActiveTasklog> edit(ActiveTasklog activeTasklog) {
        return ResponseEntity.ok(this.activeTasklogService.update(activeTasklog));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.activeTasklogService.deleteById(id));
    }

}

