package cn.zealon.readingcloud.account.controller;

import cn.zealon.readingcloud.common.pojo.xzwusers.USchool;
import cn.zealon.readingcloud.account.service.USchoolService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户学校表(USchool)表控制层
 *
 * @author makejava
 * @since 2023-03-06 09:23:22
 */
@Api(description = "学校接口")
@RestController
@RequestMapping("account/uSchool")
public class USchoolController {
    /**
     * 服务对象
     */
    @Resource
    private USchoolService uSchoolService;

    /**
     * 分页查询
     *
     * @param uSchool     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<USchool>> queryByPage(USchool uSchool, PageRequest pageRequest) {
        return ResponseEntity.ok(this.uSchoolService.queryByPage(uSchool, pageRequest));
    }

    @GetMapping("queryAll")
    public List<USchool>queryAll(USchool uSchool){
        return this.uSchoolService.queryAll(uSchool);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<USchool> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.uSchoolService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param uSchool 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<USchool> add(USchool uSchool) {
        return ResponseEntity.ok(this.uSchoolService.insert(uSchool));
    }

    /**
     * 编辑数据
     *
     * @param uSchool 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<USchool> edit(USchool uSchool) {
        return ResponseEntity.ok(this.uSchoolService.update(uSchool));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.uSchoolService.deleteById(id));
    }

}

