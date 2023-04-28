package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.HotWords;
import cn.zealon.readingcloud.book.service.HotWordsService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 热搜表(HotWords)表控制层
 *
 * @author makejava
 * @since 2023-04-27 15:24:34
 */
@Api(description = "热搜词接口")
@RestController
@RequestMapping("book/hotWords")
public class HotWordsController {
    /**
     * 服务对象
     */
    @Resource
    private HotWordsService hotWordsService;

    /**
     * 分页查询
     *
     * @param hotWords    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<HotWords>> queryByPage(HotWords hotWords, PageRequest pageRequest) {
        return ResponseEntity.ok(this.hotWordsService.queryByPage(hotWords, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
//    @GetMapping("{id}")
    public ResponseEntity<HotWords> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.hotWordsService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param hotWords 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<HotWords> add(HotWords hotWords) {
        return ResponseEntity.ok(this.hotWordsService.insert(hotWords));
    }

    @GetMapping("queryLikes")
    public List<HotWords> queryLikes(){
        return this.hotWordsService.queryLikes();
    }

    @GetMapping("cleanLikes")
    public void cleanLikes(){
        this.hotWordsService.cleanLikes();
    }
    /**
     * 编辑数据
     *
     * @param hotWords 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<HotWords> edit(HotWords hotWords) {
        return ResponseEntity.ok(this.hotWordsService.update(hotWords));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.hotWordsService.deleteById(id));
    }

}

