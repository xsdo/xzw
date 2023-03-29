package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.book.service.CLikesService;
import cn.zealon.readingcloud.common.pojo.xzwresources.CSubmit;
import cn.zealon.readingcloud.book.service.CSubmitService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 投稿表(CSubmit)表控制层
 *
 * @author makejava
 * @since 2023-03-10 17:51:35
 */
@Api(description = "投稿接口")
@RestController
@RequestMapping("book/cSubmit")
public class CSubmitController {
    /**
     * 服务对象
     */
    @Resource
    private CSubmitService cSubmitService;

    @Resource
    private CLikesService clikesService;
    /**
     * 分页查询
     *
     * @param cSubmit     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<CSubmit>> queryByPage(CSubmit cSubmit, PageRequest pageRequest) {
        return ResponseEntity.ok(this.cSubmitService.queryByPage(cSubmit, pageRequest));
    }
    @GetMapping("queryAll")
    public List<CSubmit>queryAll(CSubmit cSubmit){
        return this.cSubmitService.queryAll(cSubmit);
    }

    @GetMapping("queryByUserId")
    public List<CSubmit>queryByUserId(Long userId){
        CSubmit cSubmit = new CSubmit();
        cSubmit.setIsused(0);
        cSubmit.setUserId(userId);
        return this.cSubmitService.queryAll(cSubmit);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<CSubmit> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.cSubmitService.queryById(id));
    }

    @GetMapping("addSubmit")
    public ResponseEntity<CSubmit> add(Long userId,String name,String content) {
        return (this.cSubmitService.add(userId, name, content));
    }
    /**
     * 新增数据
     *
     * @param cSubmit 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<CSubmit> add(@RequestBody CSubmit cSubmit) {
        return ResponseEntity.ok(this.cSubmitService.insert(cSubmit));
    }

    /**
     * 编辑数据
     *
     * @param cSubmit 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<CSubmit> edit(CSubmit cSubmit) {
        return ResponseEntity.ok(this.cSubmitService.update(cSubmit));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.cSubmitService.deleteById(id));
    }

}

