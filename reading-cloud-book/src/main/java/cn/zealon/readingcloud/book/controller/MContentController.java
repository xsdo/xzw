package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.book.vo.MagazinesVO;
import cn.zealon.readingcloud.common.pojo.xzwresources.MContent;
import cn.zealon.readingcloud.book.service.MContentService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 杂志内容表(MContent)表控制层
 *
 * @author makejava
 * @since 2023-03-15 17:17:08
 */
@Api(description = "杂志内容接口")
@RestController
@RequestMapping("book/mContent")
public class MContentController {
    /**
     * 服务对象
     */
    @Resource
    private MContentService mContentService;

    /**
     * 分页查询
     *
     * @param mContent    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<MContent>> queryByPage(MContent mContent, PageRequest pageRequest) {
        return ResponseEntity.ok(this.mContentService.queryByPage(mContent, pageRequest));
    }

    @GetMapping("queryAll")
    public List<MContent> queryAll(MContent mContent){
        return this.mContentService.queryAll(mContent);
    }
    @GetMapping("queryByMagazineId")
    public List<MagazinesVO>queryByMagazineId(Long magazineId){
        return this.mContentService.queryByMagazinesId(magazineId);
    }


    @GetMapping("toMagazines")
    public JSONObject toMagazines(Long magazinesId, Long compositionId){
        return this.mContentService.toMagazines(magazinesId,compositionId);
    }


    @GetMapping("randMagazines")
    public void randMagazines(Long magazinesId,int size){
         this.mContentService.randMagazines(magazinesId, size);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<MContent> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.mContentService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param mContent 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<MContent> add(MContent mContent) {
        return ResponseEntity.ok(this.mContentService.insert(mContent));
    }

    /**
     * 编辑数据
     *
     * @param mContent 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<MContent> edit(MContent mContent) {
        return ResponseEntity.ok(this.mContentService.update(mContent));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.mContentService.deleteById(id));
    }

}

