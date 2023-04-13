package cn.zealon.readingcloud.account.controller;

import cn.zealon.readingcloud.account.service.StudentService;
import cn.zealon.readingcloud.common.pojo.xzwusers.Student;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 学生表(Student)表控制层
 *
 * @author makejava
 * @since 2023-04-10 15:47:44
 */
@Api(description = "学生接口")
@RestController
@RequestMapping("account/student")
public class StudentController {
    /**
     * 服务对象
     */
    @Resource
    private StudentService studentService;

    /**
     * 分页查询
     *
     * @param student     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<Student>> queryByPage(Student student, PageRequest pageRequest) {
        return ResponseEntity.ok(this.studentService.queryByPage(student, pageRequest));
    }

    @GetMapping("doBanding")
    public JSONObject doBinding(Long userId, Long teacherId, String name, String relation, String phoneNumber, int type) {
        return this.studentService.doBinding(userId, teacherId, name, relation, phoneNumber, type);
    }
    @GetMapping("conductBingding")
    public JSONObject conductBingding(Long userId,String name){
        return this.studentService.conductBingding(userId, name);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
//    @GetMapping("{id}")
    public ResponseEntity<Student> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.studentService.queryById(id));
    }

    @GetMapping("getAllStudent")
    public List<Student> getAllStudent() {
        return this.studentService.queryAll(new Student());
    }
    /**
     * 新增数据
     *
     * @param student 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<Student> add(Student student) {
        return ResponseEntity.ok(this.studentService.insert(student));
    }

    /**
     * 编辑数据
     *
     * @param student 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<Student> edit(Student student) {
        return ResponseEntity.ok(this.studentService.update(student));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.studentService.deleteById(id));
    }

}

