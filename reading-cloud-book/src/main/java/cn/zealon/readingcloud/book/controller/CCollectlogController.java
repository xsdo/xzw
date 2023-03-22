package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.CCollectlog;
import cn.zealon.readingcloud.book.service.CCollectlogService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 收藏夹文件表(CCollectlog)表控制层
 *
 * @author makejava
 * @since 2023-03-10 17:51:36
 */
@Api(description = "收藏文件接口")
@RestController
@RequestMapping("book/cCollectlog")
public class CCollectlogController {
    /**
     * 服务对象
     */
    @Resource
    private CCollectlogService cCollectlogService;

    /**
     * 分页查询
     *
     * @param cCollectlog 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<CCollectlog>> queryByPage(CCollectlog cCollectlog, PageRequest pageRequest) {
        return ResponseEntity.ok(this.cCollectlogService.queryByPage(cCollectlog, pageRequest));
    }

    @GetMapping("queryAll")
    public List<CCollectlog>queryAll(CCollectlog cCollectlog){
        return this.cCollectlogService.queryAll(cCollectlog);
    }
    @GetMapping("queryByCollectId")
    public List<CCollectlog>queryByCollectId(Long collectId){
        CCollectlog cCollectlog=new CCollectlog();
        cCollectlog.setIsused(0);
        cCollectlog.setCollectId(collectId);
        return this.cCollectlogService.queryAll(cCollectlog);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<CCollectlog> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.cCollectlogService.queryById(id));
    }


    @GetMapping("moveCollectlog")
    public ResponseEntity<CCollectlog>moveCollectlog(Long collectId,Long collectLogId){
        CCollectlog cCollectlog = this.cCollectlogService.queryById(collectLogId);
        if (cCollectlog!=null){
            cCollectlog.setCollectId(collectId);
            this.cCollectlogService.update(cCollectlog);
        }
        return ResponseEntity.ok(this.cCollectlogService.queryById(collectLogId));
    }
    @GetMapping("addCollectlog")
    public ResponseEntity<CCollectlog> addCollectlog(Long collectId,String cName ,String cImage, Long compositionId, int type) {
        CCollectlog cCollectlog = new CCollectlog();
        cCollectlog.setIsused(0);
        cCollectlog.setCreateTime(new Date());
        cCollectlog.setUpdateTime(new Date());
        cCollectlog.setCollectId(collectId);
        cCollectlog.setCName(cName);
        cCollectlog.setCImage(cImage);
        cCollectlog.setCompositionId(compositionId);
        cCollectlog.setCType(type);
        return ResponseEntity.ok(this.cCollectlogService.insert(cCollectlog));
    }

    /**
     * 新增数据
     *
     * @param cCollectlog 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<CCollectlog> add(CCollectlog cCollectlog) {
        cCollectlog.setIsused(0);
        cCollectlog.setCreateTime(new Date());
        cCollectlog.setUpdateTime(new Date());
        return ResponseEntity.ok(this.cCollectlogService.insert(cCollectlog));
    }

    /**
     * 编辑数据
     *
     * @param cCollectlog 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<CCollectlog> edit(CCollectlog cCollectlog) {
        return ResponseEntity.ok(this.cCollectlogService.update(cCollectlog));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.cCollectlogService.deleteById(id));
    }

}

