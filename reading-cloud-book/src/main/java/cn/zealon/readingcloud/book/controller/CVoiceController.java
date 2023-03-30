package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.CVoice;
import cn.zealon.readingcloud.book.service.CVoiceService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 音频表(CVoice)表控制层
 *
 * @author makejava
 * @since 2023-03-01 11:14:00
 */
@Api(description = "音频接口")
@RestController
@RequestMapping("book/cVoice")
public class CVoiceController {
    /**
     * 服务对象
     */
    @Resource
    private CVoiceService cVoiceService;

    /**
     * 分页查询
     *
     * @param cVoice      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<CVoice>> queryByPage(CVoice cVoice, PageRequest pageRequest) {
        return ResponseEntity.ok(this.cVoiceService.queryByPage(cVoice, pageRequest));
    }

    @GetMapping("queryAll")
    public List<CVoice>queryAll(CVoice cVoice){
        return this.cVoiceService.queryAll(cVoice);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<CVoice> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.cVoiceService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param cVoice 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<CVoice> add(CVoice cVoice) {
        return ResponseEntity.ok(this.cVoiceService.insert(cVoice));
    }

    /**
     * 编辑数据
     *
     * @param cVoice 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<CVoice> edit(CVoice cVoice) {
        return ResponseEntity.ok(this.cVoiceService.update(cVoice));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.cVoiceService.deleteById(id));
    }

}

