package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.CNation;
import cn.zealon.readingcloud.book.service.CNationService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 全国作文表(CNation)表控制层
 *
 * @author makejava
 * @since 2023-03-01 11:13:56
 */
@Api(description = "全国作文接口")
@RestController
@RequestMapping("book/cNation")
public class CNationController {
    /**
     * 服务对象
     */
    @Resource
    private CNationService cNationService;

    /**
     * 分页查询
     *
     * @param cNation     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<CNation>> queryByPage(CNation cNation, PageRequest pageRequest) {
        return ResponseEntity.ok(this.cNationService.queryByPage(cNation, pageRequest));
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<CNation> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.cNationService.queryById(id));
    }

    @GetMapping("queryAll")
    public List<CNation>queryAll(CNation cNation){
    return this.cNationService.queryAll(cNation);
    }

    @GetMapping("randNation")
    public List<CNation> randNation(int size){
        return this.cNationService.randNation(size);
    }

    /**
     * 新增数据
     *
     * @param cNation 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<CNation> add(CNation cNation) {
        return ResponseEntity.ok(this.cNationService.insert(cNation));
    }

    /**
     * 编辑数据
     *
     * @param cNation 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<CNation> edit(CNation cNation) {
        return ResponseEntity.ok(this.cNationService.update(cNation));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.cNationService.deleteById(id));
    }

}

