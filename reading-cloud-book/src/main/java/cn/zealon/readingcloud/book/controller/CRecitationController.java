package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.CRecitation;
import cn.zealon.readingcloud.book.service.CRecitationService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 朗读者表(CRecitation)表控制层
 *
 * @author makejava
 * @since 2023-03-01 11:13:57
 */
@Api(description = "朗读者接口")
@RestController
@RequestMapping("book/cRecitation")
public class CRecitationController {
    /**
     * 服务对象
     */
    @Resource
    private CRecitationService cRecitationService;

    /**
     * 分页查询
     *
     * @param cRecitation 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<CRecitation>> queryByPage(CRecitation cRecitation, PageRequest pageRequest) {
        return ResponseEntity.ok(this.cRecitationService.queryByPage(cRecitation, pageRequest));
    }

    @GetMapping("/queryAll")
    public List<CRecitation> queryAll(CRecitation cRecitation){
        return this.cRecitationService.queryAll(cRecitation);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<CRecitation> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.cRecitationService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param cRecitation 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<CRecitation> add(CRecitation cRecitation) {
        return ResponseEntity.ok(this.cRecitationService.insert(cRecitation));
    }

    /**
     * 编辑数据
     *
     * @param cRecitation 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<CRecitation> edit(CRecitation cRecitation) {
        return ResponseEntity.ok(this.cRecitationService.update(cRecitation));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.cRecitationService.deleteById(id));
    }

}

