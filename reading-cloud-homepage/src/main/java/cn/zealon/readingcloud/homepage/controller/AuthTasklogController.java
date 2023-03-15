package cn.zealon.readingcloud.homepage.controller;

import cn.zealon.readingcloud.common.cache.RedisService;
import cn.zealon.readingcloud.common.pojo.xzwtasks.AuthTasklog;
import cn.zealon.readingcloud.homepage.service.AuthTasklogService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static cn.zealon.readingcloud.common.cache.RedisExpire.DAY;

/**
 * 认证用户记录表(AuthTasklog)表控制层
 *
 * @author makejava
 * @since 2023-03-01 11:11:42
 */
@Api(description = "认证用户记录")
@RestController
@RequestMapping("index/authTasklog")
public class AuthTasklogController {
    /**
     * 服务对象
     */
    @Resource
    private AuthTasklogService authTasklogService;

    @Resource
    private RedisService redisService;
    /**
     * 分页查询
     *
     * @param authTasklog 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<AuthTasklog>> queryByPage(AuthTasklog authTasklog, PageRequest pageRequest) {
        return ResponseEntity.ok(this.authTasklogService.queryByPage(authTasklog, pageRequest));
    }

    @GetMapping("queryAll")
    public List<AuthTasklog>queryAll(AuthTasklog authTasklog){
        return this.authTasklogService.queryAll(authTasklog);
    }
    @GetMapping("queryByUserId")
    public List<AuthTasklog>queryByUserId(Long userId){
        return this.authTasklogService.queryByUserId(userId);
    }

    @GetMapping("toAuthTasklog")
    public JSONObject toAuthTasklog(Long authTasklogId){
        return this.authTasklogService.toAuthTasklog(authTasklogId);
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<AuthTasklog> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.authTasklogService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param authTasklog 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<AuthTasklog> add(AuthTasklog authTasklog) {
        return ResponseEntity.ok(this.authTasklogService.insert(authTasklog));
    }

    /**
     * 编辑数据
     *
     * @param authTasklog 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<AuthTasklog> edit(AuthTasklog authTasklog) {
        return ResponseEntity.ok(this.authTasklogService.update(authTasklog));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.authTasklogService.deleteById(id));
    }

}

