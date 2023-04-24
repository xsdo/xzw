package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.book.service.ReadEnrollService;
import cn.zealon.readingcloud.common.pojo.xzwresources.ReadEnroll;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 活动报名表(ReadEnroll)表控制层
 *
 * @author makejava
 * @since 2023-04-17 09:28:18
 */
@Api(description = "活动报名接口")
@RestController
@RequestMapping("book/readEnroll")
public class ReadEnrollController {
    /**
     * 服务对象
     */
    @Resource
    private ReadEnrollService readEnrollService;

    /**
     * 分页查询
     *
     * @param readEnroll  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<ReadEnroll>> queryByPage(ReadEnroll readEnroll, PageRequest pageRequest) {
        return ResponseEntity.ok(this.readEnrollService.queryByPage(readEnroll, pageRequest));
    }


    @GetMapping("queryAll")
    public List<ReadEnroll> queryAll(ReadEnroll readEnroll){
        return this.readEnrollService.queryAll(readEnroll);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ReadEnroll> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.readEnrollService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param readEnroll 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<ReadEnroll> add(ReadEnroll readEnroll) {
        return ResponseEntity.ok(this.readEnrollService.insert(readEnroll));
    }

    @ApiOperation(value = "新增报名")
    @PostMapping
    public ResponseEntity<ReadEnroll> insertReadEnrol(ReadEnroll readEnroll) {
        readEnroll.setCreateTime(new Date());
        readEnroll.setUpdateTime(new Date());
        readEnroll.setIsused(0);
        return ResponseEntity.ok(this.readEnrollService.insert(readEnroll));
    }

    /**
     * 编辑数据
     *
     * @param readEnroll 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<ReadEnroll> edit(ReadEnroll readEnroll) {
        return ResponseEntity.ok(this.readEnrollService.update(readEnroll));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.readEnrollService.deleteById(id));
    }

}

