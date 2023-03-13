package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.MColumn;
import cn.zealon.readingcloud.book.service.MColumnService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 杂志栏目表(MColumn)表控制层
 *
 * @author makejava
 * @since 2023-03-01 11:14:00
 */
@Api(description = "杂志栏目接口")
@RestController
@RequestMapping("book/mColumn")
public class MColumnController {
    /**
     * 服务对象
     */
    @Resource
    private MColumnService mColumnService;

    /**
     * 分页查询
     *
     * @param mColumn     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<MColumn>> queryByPage(MColumn mColumn, PageRequest pageRequest) {
        return ResponseEntity.ok(this.mColumnService.queryByPage(mColumn, pageRequest));
    }

    @GetMapping("queryALL")
    public List<MColumn>queryAll(MColumn mColumn){
        return this.mColumnService.queryAll(mColumn);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<MColumn> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.mColumnService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param mColumn 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<MColumn> add(MColumn mColumn) {
        return ResponseEntity.ok(this.mColumnService.insert(mColumn));
    }

    /**
     * 编辑数据
     *
     * @param mColumn 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<MColumn> edit(MColumn mColumn) {
        return ResponseEntity.ok(this.mColumnService.update(mColumn));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.mColumnService.deleteById(id));
    }

}

