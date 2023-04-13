package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.Department;
import cn.zealon.readingcloud.book.service.DepartmentService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * 部编同步作文(Department)表控制层
 *
 * @author makejava
 * @since 2023-03-01 11:13:55
 */
@Api(description = "部编同步作文接口")
@RestController
@RequestMapping("book/department")
public class DepartmentController {
    /**
     * 服务对象
     */
    @Resource
    private DepartmentService departmentService;

    /**
     * 分页查询
     *
     * @param department  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<Department>> queryByPage(Department department, PageRequest pageRequest) {
        return ResponseEntity.ok(this.departmentService.queryByPage(department, pageRequest));
    }

    @GetMapping("queryAll")
    public List<Department>queryAll(Department department){
        return this.departmentService.queryAll(department);
    }

    @GetMapping("queryByDepartment")
    public List<Department>queryByDepartment(String dGrade,String dUnitarea) {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        String term="";
        if (month >= 9) {
            term = "上学期";
        } else {
            term = "下学期";
        }
        Department department=new Department();
        department.setDTerm(term);
        department.setDGrade(dGrade);
        department.setDUnitarea(dUnitarea);
        return this.departmentService.queryAll(department);
    }

    //检阅作文数据
//    @GetMapping("/queryContent")
    public List<Map<String,String>>queryContent(){
        return this.departmentService.queryContent();
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Department> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.departmentService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param department 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<Department> add(Department department) {
        return ResponseEntity.ok(this.departmentService.insert(department));
    }

    /**
     * 编辑数据
     *
     * @param department 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<Department> edit(Department department) {
        return ResponseEntity.ok(this.departmentService.update(department));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.departmentService.deleteById(id));
    }

}

