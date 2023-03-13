package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.MTitles;
import cn.zealon.readingcloud.book.service.MTitlesService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 杂志目录表(MTitles)表控制层
 *
 * @author makejava
 * @since 2023-03-01 11:13:53
 */
@Api(description = "杂志目录接口")
@RestController
@RequestMapping("book/mTitles")
public class MTitlesController {
    /**
     * 服务对象
     */
    @Resource
    private MTitlesService mTitlesService;

    /**
     * 分页查询
     *
     * @param mTitles     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<MTitles>> queryByPage(MTitles mTitles, PageRequest pageRequest) {
        return ResponseEntity.ok(this.mTitlesService.queryByPage(mTitles, pageRequest));
    }

    @GetMapping("queryAll")
    public List<MTitles>queryAll(MTitles mTitles){
        return this.mTitlesService.queryAll(mTitles);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<MTitles> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.mTitlesService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param mTitles 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<MTitles> add(MTitles mTitles) {
        return ResponseEntity.ok(this.mTitlesService.insert(mTitles));
    }

    /**
     * 编辑数据
     *
     * @param mTitles 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<MTitles> edit(MTitles mTitles) {
        return ResponseEntity.ok(this.mTitlesService.update(mTitles));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.mTitlesService.deleteById(id));
    }

}

