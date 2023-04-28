package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.StudentShareData;
import cn.zealon.readingcloud.book.service.StudentShareDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 学生分享表(StudentShareData)表控制层
 *
 * @author makejava
 * @since 2023-04-26 10:21:08
 */
@Api(description = "学生分享接口")
@RestController
@RequestMapping("book/studentShareData")
public class StudentShareDataController {
    /**
     * 服务对象
     */
    @Resource
    private StudentShareDataService studentShareDataService;

    /**
     * 分页查询
     *
     * @param studentShareData 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<StudentShareData>> queryByPage(StudentShareData studentShareData, PageRequest pageRequest) {
        return ResponseEntity.ok(this.studentShareDataService.queryByPage(studentShareData, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
//    @GetMapping("{id}")
    public ResponseEntity<StudentShareData> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.studentShareDataService.queryById(id));
    }

    @ApiOperation(value = "根据用户id查询chat次数")
    @GetMapping("queryChatNumberByUserId")
    public StudentShareData queryChatNumberByUserId(Long userId){
        return this.studentShareDataService.queryChatNumberByUserId(userId);
    }


    @GetMapping("queryNumberByUserId")
    public int queryNumberByUserId(Long userId){
        return this.studentShareDataService.queryNumberByUserId(userId);
    }


    @ApiOperation(value = "用户分享增加chat次数")
    @GetMapping("doShare")
    public StudentShareData doShare(Long userId){
        return this.studentShareDataService.doShare(userId);
    }

    /**
     * 新增数据
     *
     * @param studentShareData 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<StudentShareData> add(StudentShareData studentShareData) {
        return ResponseEntity.ok(this.studentShareDataService.insert(studentShareData));
    }

    /**
     * 编辑数据
     *
     * @param studentShareData 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<StudentShareData> edit(StudentShareData studentShareData) {
        return ResponseEntity.ok(this.studentShareDataService.update(studentShareData));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.studentShareDataService.deleteById(id));
    }

}

