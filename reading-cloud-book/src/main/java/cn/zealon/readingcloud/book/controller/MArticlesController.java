package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.MArticles;
import cn.zealon.readingcloud.book.service.MArticlesService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 杂志文章表(MArticles)表控制层
 *
 * @author makejava
 * @since 2023-03-01 11:13:59
 */
@Api(description = "杂志文章接口")
@RestController
@RequestMapping("book/mArticles")
public class MArticlesController {
    /**
     * 服务对象
     */
    @Resource
    private MArticlesService mArticlesService;

    /**
     * 分页查询
     *
     * @param mArticles   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<MArticles>> queryByPage(MArticles mArticles, PageRequest pageRequest) {
        return ResponseEntity.ok(this.mArticlesService.queryByPage(mArticles, pageRequest));
    }

    @GetMapping("queryAll")
    public List<MArticles>queryAll(MArticles mArticles){
        return this.mArticlesService.queryAll(mArticles);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<MArticles> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.mArticlesService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param mArticles 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<MArticles> add(MArticles mArticles) {
        return ResponseEntity.ok(this.mArticlesService.insert(mArticles));
    }

    /**
     * 编辑数据
     *
     * @param mArticles 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<MArticles> edit(MArticles mArticles) {
        return ResponseEntity.ok(this.mArticlesService.update(mArticles));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.mArticlesService.deleteById(id));
    }

}

