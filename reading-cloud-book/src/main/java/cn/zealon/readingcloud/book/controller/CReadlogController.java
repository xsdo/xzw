package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.CReadlog;
import cn.zealon.readingcloud.book.service.CReadlogService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 阅读记录表(CReadlog)表控制层
 *
 * @author makejava
 * @since 2023-03-10 17:51:34
 */
@Api(description = "阅读记录接口")
@RestController
@RequestMapping("book/cReadlog")
public class CReadlogController {
    /**
     * 服务对象
     */
    @Resource
    private CReadlogService cReadlogService;

    /**
     * 分页查询
     *
     * @param cReadlog    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<CReadlog>> queryByPage(CReadlog cReadlog, PageRequest pageRequest) {
        return ResponseEntity.ok(this.cReadlogService.queryByPage(cReadlog, pageRequest));
    }

    @GetMapping("queryAll")
    public List<CReadlog>queryAll(CReadlog cReadlog){
        return this.cReadlogService.queryAll(cReadlog);
    }

    @GetMapping("queryByUserId")
    public List<CReadlog>queryByUserId(Long userId){
        CReadlog cReadlog =new CReadlog();
        cReadlog.setIsused(0);
        cReadlog.setUserId(userId);
        return this.cReadlogService.queryAll(cReadlog);
    }

    @GetMapping("doReadlog")
    public CReadlog doReadlog(Long userId,String image,String name,Long compositionId,int type){
        return this.cReadlogService.doReadlog(userId, image, name, compositionId, type);
    }

    @GetMapping("cleanReadlog")
    public void cleanReadlog(Long userId){
        this.cReadlogService.cleanReadlog(userId);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<CReadlog> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.cReadlogService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param cReadlog 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<CReadlog> add(CReadlog cReadlog) {
        return ResponseEntity.ok(this.cReadlogService.insert(cReadlog));
    }

    /**
     * 编辑数据
     *
     * @param cReadlog 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<CReadlog> edit(CReadlog cReadlog) {
        return ResponseEntity.ok(this.cReadlogService.update(cReadlog));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.cReadlogService.deleteById(id));
    }

}

