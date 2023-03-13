package cn.zealon.readingcloud.account.controller;

import cn.zealon.readingcloud.common.pojo.xzwusers.UTable;
import cn.zealon.readingcloud.account.service.UTableService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户标签表(UTable)表控制层
 *
 * @author makejava
 * @since 2023-03-01 10:43:55
 */
@Api(description = "用户标签接口")
@RestController
@RequestMapping("account/uTable")
public class UTableController {
    /**
     * 服务对象
     */
    @Resource
    private UTableService uTableService;

    /**
     * 分页查询
     *
     * @param uTable      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<UTable>> queryByPage(UTable uTable, PageRequest pageRequest) {
        return ResponseEntity.ok(this.uTableService.queryByPage(uTable, pageRequest));
    }

    @GetMapping("queryAll")
    public List<UTable>queryAll(UTable uTable){
        return this.uTableService.queryAll(uTable);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<UTable> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.uTableService.queryById(id));
    }

    @GetMapping("queryByIds")
    public List<UTable>queryByIds(String ids){
        String[] idss=ids.split(",");
        ArrayList idList = new ArrayList<>();
        for (String id: idss) {
            idList.add(id);
        }

        return this.uTableService.queryByIds(idList);
    }
    /**
     * 新增数据
     *
     * @param uTable 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<UTable> add(UTable uTable) {
        return ResponseEntity.ok(this.uTableService.insert(uTable));
    }

    /**
     * 编辑数据
     *
     * @param uTable 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<UTable> edit(UTable uTable) {
        return ResponseEntity.ok(this.uTableService.update(uTable));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.uTableService.deleteById(id));
    }

}

