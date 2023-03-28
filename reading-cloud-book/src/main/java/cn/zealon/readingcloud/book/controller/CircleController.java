package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.Circle;
import cn.zealon.readingcloud.book.service.CircleService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 圈子表(Circle)表控制层
 *
 * @author makejava
 * @since 2023-03-08 17:40:19
 */
@Api(description = "圈子接口")
@RestController
@RequestMapping("book/circle")
public class CircleController {
    /**
     * 服务对象
     */
    @Resource
    private CircleService circleService;

    /**
     * 分页查询
     *
     * @param circle      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Circle>> queryByPage(Circle circle, PageRequest pageRequest) {
        return ResponseEntity.ok(this.circleService.queryByPage(circle, pageRequest));
    }

    @GetMapping("queryAll")
    public List<Circle> queryAll(Circle circle){
        return circleService.queryAll(circle);
    }

    @GetMapping("queryByUserId")
    public List<Circle>queryByUserId(Long userId){
        return this.circleService.queryByUserId(userId);
    }



    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Circle> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.circleService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param circle 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Circle> add(Circle circle) {
        return ResponseEntity.ok(this.circleService.insert(circle));
    }

    /**
     * 编辑数据
     *
     * @param circle 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Circle> edit(Circle circle) {
        return ResponseEntity.ok(this.circleService.update(circle));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.circleService.deleteById(id));
    }

}

