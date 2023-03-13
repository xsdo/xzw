package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.CSchool;
import cn.zealon.readingcloud.book.service.CSchoolService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 校园作文表(CSchool)表控制层
 *
 * @author makejava
 * @since 2023-03-01 11:13:57
 */
@Api(description = "校园作文接口")
@RestController
@RequestMapping("book/cSchool")
public class CSchoolController {
    /**
     * 服务对象
     */
    @Resource
    private CSchoolService cSchoolService;

    /**
     * 分页查询
     *
     * @param cSchool     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<CSchool>> queryByPage(CSchool cSchool, PageRequest pageRequest) {
        return ResponseEntity.ok(this.cSchoolService.queryByPage(cSchool, pageRequest));
    }

    @GetMapping("queryAll")
    public List<CSchool> queryAll(CSchool cSchool){
        return this.cSchoolService.queryAll(cSchool);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<CSchool> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.cSchoolService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param cSchool 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<CSchool> add(CSchool cSchool) {
        return ResponseEntity.ok(this.cSchoolService.insert(cSchool));
    }

    /**
     * 编辑数据
     *
     * @param cSchool 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<CSchool> edit(CSchool cSchool) {
        return ResponseEntity.ok(this.cSchoolService.update(cSchool));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.cSchoolService.deleteById(id));
    }

}

