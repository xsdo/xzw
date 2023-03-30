package cn.zealon.readingcloud.account.controller;

import cn.zealon.readingcloud.common.pojo.xzwusers.UCharglog;
import cn.zealon.readingcloud.account.service.UCharglogService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 充值记录表(UCharglog)表控制层
 *
 * @author makejava
 * @since 2023-03-10 17:54:34
 */
@Api(description = "充值记录接口")
@RestController
@RequestMapping("account/uCharglog")
public class UCharglogController {
    /**
     * 服务对象
     */
    @Resource
    private UCharglogService uCharglogService;

    /**
     * 分页查询
     *
     * @param uCharglog   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<UCharglog>> queryByPage(UCharglog uCharglog, PageRequest pageRequest) {
        return ResponseEntity.ok(this.uCharglogService.queryByPage(uCharglog, pageRequest));
    }


//    @GetMapping("queryAll")
    public List<UCharglog>queryAll(UCharglog uCharglog){
        return this.uCharglogService.queryAll(uCharglog);
    }


    @GetMapping("queryByUserId")
    public List<UCharglog>queryByUserId(Long userId){
        return this.uCharglogService.queryByUserId(userId);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
//    @GetMapping("{id}")
    public ResponseEntity<UCharglog> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.uCharglogService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param uCharglog 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<UCharglog> add(UCharglog uCharglog) {
        return ResponseEntity.ok(this.uCharglogService.insert(uCharglog));
    }

    /**
     * 编辑数据
     *
     * @param uCharglog 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<UCharglog> edit(UCharglog uCharglog) {
        return ResponseEntity.ok(this.uCharglogService.update(uCharglog));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.uCharglogService.deleteById(id));
    }

}

