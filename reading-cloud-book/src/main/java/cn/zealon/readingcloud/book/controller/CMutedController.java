package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.CMuted;
import cn.zealon.readingcloud.book.service.CMutedService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 禁言表(CMuted)表控制层
 *
 * @author makejava
 * @since 2023-03-01 11:14:01
 */
@Api(description = "禁言接口")
@RestController
@RequestMapping("book/cMuted")
public class CMutedController {
    /**
     * 服务对象
     */
    @Resource
    private CMutedService cMutedService;

    /**
     * 分页查询
     *
     * @param cMuted      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<CMuted>> queryByPage(CMuted cMuted, PageRequest pageRequest) {
        return ResponseEntity.ok(this.cMutedService.queryByPage(cMuted, pageRequest));
    }

    @GetMapping("queryAll")
    public List<CMuted>queryAll(CMuted cMuted){
        return this.cMutedService.queryAll(cMuted);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
//    @GetMapping("{id}")
    public ResponseEntity<CMuted> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.cMutedService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param cMuted 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<CMuted> add(CMuted cMuted) {
        return ResponseEntity.ok(this.cMutedService.insert(cMuted));
    }

    /**
     * 编辑数据
     *
     * @param cMuted 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<CMuted> edit(CMuted cMuted) {
        return ResponseEntity.ok(this.cMutedService.update(cMuted));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.cMutedService.deleteById(id));
    }

}

