package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.account.feign.client.UserAttributeClient;
import cn.zealon.readingcloud.common.pojo.xzwresources.CNote;
import cn.zealon.readingcloud.book.service.CNoteService;
import cn.zealon.readingcloud.common.pojo.xzwusers.UAttribute;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 笔记表(CNote)表控制层
 *
 * @author makejava
 * @since 2023-03-08 14:27:21
 */
@Api(description = "笔记接口")
@RestController
@RequestMapping("book/cNote")
public class CNoteController {
    /**
     * 服务对象
     */
    @Resource
    private CNoteService cNoteService;

    /**
     * 分页查询
     *
     * @param cNote       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<CNote>> queryByPage(CNote cNote, PageRequest pageRequest) {
        return ResponseEntity.ok(this.cNoteService.queryByPage(cNote, pageRequest));
    }

    @GetMapping("queryAll")
    public List<CNote> queryAll(CNote cNote){
        return this.cNoteService.queryAll(cNote);
    }

    @GetMapping("queryByUserId")
    public List<CNote> queryByUserId(Long userId){
        CNote cNote =new CNote();
        cNote.setIsused(0);
        cNote.setUserId(userId);
        return this.cNoteService.queryAll(cNote);
    }

    @PostMapping("doNote")
    public JSONObject doNode(Long userId,String note){
        return  this.cNoteService.doNode(userId,note);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
//    @GetMapping("{id}")
    public ResponseEntity<CNote> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.cNoteService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param cNote 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<CNote> add(CNote cNote) {
        return ResponseEntity.ok(this.cNoteService.insert(cNote));
    }

    /**
     * 编辑数据
     *
     * @param cNote 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<CNote> edit(CNote cNote) {
        return ResponseEntity.ok(this.cNoteService.update(cNote));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.cNoteService.deleteById(id));
    }

}

