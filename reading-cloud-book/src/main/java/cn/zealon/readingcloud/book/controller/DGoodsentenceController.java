package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.DGoodsentence;
import cn.zealon.readingcloud.book.service.DGoodsentenceService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部编好句表(DGoodsentence)表控制层
 *
 * @author makejava
 * @since 2023-03-01 11:13:53
 */
@Api(description = "部编好句接口")
@RestController
@RequestMapping("book/dGoodsentence")
public class DGoodsentenceController {
    /**
     * 服务对象
     */
    @Resource
    private DGoodsentenceService dGoodsentenceService;

    /**
     * 分页查询
     *
     * @param dGoodsentence 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<DGoodsentence>> queryByPage(DGoodsentence dGoodsentence, PageRequest pageRequest) {
        return ResponseEntity.ok(this.dGoodsentenceService.queryByPage(dGoodsentence, pageRequest));
    }

    @GetMapping("queryAll")
    public List<DGoodsentence>queryAll(DGoodsentence dGoodsentence){
        return this.dGoodsentenceService.queryAll(dGoodsentence);
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<DGoodsentence> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.dGoodsentenceService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param dGoodsentence 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<DGoodsentence> add(DGoodsentence dGoodsentence) {
        return ResponseEntity.ok(this.dGoodsentenceService.insert(dGoodsentence));
    }

    /**
     * 编辑数据
     *
     * @param dGoodsentence 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<DGoodsentence> edit(DGoodsentence dGoodsentence) {
        return ResponseEntity.ok(this.dGoodsentenceService.update(dGoodsentence));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.dGoodsentenceService.deleteById(id));
    }

}

