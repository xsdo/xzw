package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.CCollect;
import cn.zealon.readingcloud.book.service.CCollectService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 收藏表(CCollect)表控制层
 *
 * @author makejava
 * @since 2023-03-10 17:51:36
 */
@Api(description = "收藏接口")
@RestController
@RequestMapping("book/cCollect")
public class CCollectController {
    /**
     * 服务对象
     */
    @Resource
    private CCollectService cCollectService;

    /**
     * 分页查询
     *
     * @param cCollect    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<CCollect>> queryByPage(CCollect cCollect, PageRequest pageRequest) {
        return ResponseEntity.ok(this.cCollectService.queryByPage(cCollect, pageRequest));
    }

//    @GetMapping("queryAll")
    public List<CCollect>queryAll(CCollect cCollect){
        return this.cCollectService.queryAll(cCollect);
    }

    @GetMapping("queryByUserId")
    public List<CCollect>queryByUserId(Long userId){
        return this.cCollectService.queryByUserId(userId);
    }



    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<CCollect> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.cCollectService.queryById(id));
    }

    @GetMapping("addCollect")
    public ResponseEntity<CCollect> addCollect(Long userId,String cName) {
        CCollect cCollect=new CCollect();
        cCollect.setIsused(0);
        cCollect.setCreateTime(new Date());
        cCollect.setUpdateTime(new Date());
        cCollect.setUserId(userId);
        cCollect.setCName(cName);
        return ResponseEntity.ok(this.cCollectService.insert(cCollect));
    }

    @PostMapping("editConllect")
    public ResponseEntity<CCollect> editConllect(Long id,Long userId,String cName) {
        CCollect cCollect=new CCollect();
        cCollect.setId(id);
        cCollect.setIsused(0);
        cCollect.setUpdateTime(new Date());
        cCollect.setUserId(userId);
        cCollect.setCName(cName);
        return ResponseEntity.ok(this.cCollectService.update(cCollect));
    }
    /**
     * 新增数据
     *
     * @param cCollect 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<CCollect> add(CCollect cCollect) {
        cCollect.setIsused(0);
        cCollect.setCreateTime(new Date());
        cCollect.setUpdateTime(new Date());
        return ResponseEntity.ok(this.cCollectService.insert(cCollect));
    }

    /**
     * 编辑数据
     *
     * @param cCollect 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<CCollect> edit(CCollect cCollect) {
        return ResponseEntity.ok(this.cCollectService.update(cCollect));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.cCollectService.deleteById(id));
    }

}

