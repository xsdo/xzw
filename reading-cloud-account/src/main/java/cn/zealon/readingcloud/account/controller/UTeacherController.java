package cn.zealon.readingcloud.account.controller;

import cn.zealon.readingcloud.account.service.UTeacherService;
import cn.zealon.readingcloud.common.pojo.xzwusers.UTeacher;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教师班级表(UTeacher)表控制层
 *
 * @author makejava
 * @since 2023-03-06 09:23:22
 */
@Api(description = "班级老师接口")
@RestController
@RequestMapping("account/uTeacher")
public class UTeacherController {
    /**
     * 服务对象
     */
    @Resource
    private UTeacherService uTeacherService;

    /**
     * 分页查询
     *
     * @param uTeacher    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<UTeacher>> queryByPage(UTeacher uTeacher, PageRequest pageRequest) {
        return ResponseEntity.ok(this.uTeacherService.queryByPage(uTeacher, pageRequest));
    }

    @GetMapping("queryAll")
    public List<UTeacher>queryAll(UTeacher uTeacher){
        return this.uTeacherService.queryAll(uTeacher);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<UTeacher> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.uTeacherService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param uTeacher 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<UTeacher> add(UTeacher uTeacher) {
        return ResponseEntity.ok(this.uTeacherService.insert(uTeacher));
    }

    /**
     * 编辑数据
     *
     * @param uTeacher 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<UTeacher> edit(UTeacher uTeacher) {
        return ResponseEntity.ok(this.uTeacherService.update(uTeacher));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.uTeacherService.deleteById(id));
    }

}

