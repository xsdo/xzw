package cn.zealon.readingcloud.account.controller;

import cn.zealon.readingcloud.common.pojo.xzwusers.UFans;
import cn.zealon.readingcloud.account.service.UFansService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户粉丝表(UFans)表控制层
 *
 * @author makejava
 * @since 2023-03-01 10:43:53
 */
//@Api(description = "用户粉丝接口")
//@RestController
//@RequestMapping("account/uFans")
public class UFansController {
    /**
     * 服务对象
     */
    @Resource
    private UFansService uFansService;

    /**
     * 分页查询
     *
     * @param uFans       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<UFans>> queryByPage(UFans uFans, PageRequest pageRequest) {
        return ResponseEntity.ok(this.uFansService.queryByPage(uFans, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<UFans> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.uFansService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param uFans 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<UFans> add(UFans uFans) {
        return ResponseEntity.ok(this.uFansService.insert(uFans));
    }

    /**
     * 编辑数据
     *
     * @param uFans 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<UFans> edit(UFans uFans) {
        return ResponseEntity.ok(this.uFansService.update(uFans));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.uFansService.deleteById(id));
    }

}

