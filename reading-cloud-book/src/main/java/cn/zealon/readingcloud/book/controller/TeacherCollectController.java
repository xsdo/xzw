package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.TeacherCollect;
import cn.zealon.readingcloud.book.service.TeacherCollectService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教师收藏表(TeacherCollect)表控制层
 *
 * @author makejava
 * @since 2023-04-10 15:47:48
 */
@Api(description = "教师收藏接口")
@RestController
@RequestMapping("book/teacherCollect")
public class TeacherCollectController {
    /**
     * 服务对象
     */
    @Resource
    private TeacherCollectService teacherCollectService;

    /**
     * 分页查询
     *
     * @param teacherCollect 筛选条件
     * @param pageRequest    分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<TeacherCollect>> queryByPage(TeacherCollect teacherCollect, PageRequest pageRequest) {
        return ResponseEntity.ok(this.teacherCollectService.queryByPage(teacherCollect, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
//    @GetMapping("{id}")
    public ResponseEntity<TeacherCollect> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.teacherCollectService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param teacherCollect 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<TeacherCollect> add(TeacherCollect teacherCollect) {
        return ResponseEntity.ok(this.teacherCollectService.insert(teacherCollect));
    }

    @GetMapping("addCollect")
    public List<TeacherCollect> addCollect(Long userId, Long compositionId, int type) {
        return this.teacherCollectService.addCollect (userId,compositionId,type);
    }

    /**
     * 编辑数据
     *
     * @param teacherCollect 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<TeacherCollect> edit(TeacherCollect teacherCollect) {
        return ResponseEntity.ok(this.teacherCollectService.update(teacherCollect));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.teacherCollectService.deleteById(id));
    }

}

