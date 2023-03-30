package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.DGoodwords;
import cn.zealon.readingcloud.book.service.DGoodwordsService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部编好词表(DGoodwords)表控制层
 *
 * @author makejava
 * @since 2023-03-01 11:13:59
 */
@Api(description = "部编好词接口")
@RestController
@RequestMapping("book/dGoodwords")
public class DGoodwordsController {
    /**
     * 服务对象
     */
    @Resource
    private DGoodwordsService dGoodwordsService;

    /**
     * 分页查询
     *
     * @param dGoodwords  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<DGoodwords>> queryByPage(DGoodwords dGoodwords, PageRequest pageRequest) {
        return ResponseEntity.ok(this.dGoodwordsService.queryByPage(dGoodwords, pageRequest));
    }

    @GetMapping("queryAll")
    public List<DGoodwords>queryAll(DGoodwords dGoodwords){
        return this.dGoodwordsService.queryAll(dGoodwords);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<DGoodwords> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.dGoodwordsService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param dGoodwords 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<DGoodwords> add(DGoodwords dGoodwords) {
        return ResponseEntity.ok(this.dGoodwordsService.insert(dGoodwords));
    }

    /**
     * 编辑数据
     *
     * @param dGoodwords 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<DGoodwords> edit(DGoodwords dGoodwords) {
        return ResponseEntity.ok(this.dGoodwordsService.update(dGoodwords));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.dGoodwordsService.deleteById(id));
    }

}

