package cn.zealon.readingcloud.account.controller;

import cn.zealon.readingcloud.common.pojo.xzwusers.USigndetail;
import cn.zealon.readingcloud.account.service.USigndetailService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 签到明细表(USigndetail)表控制层
 *
 * @author makejava
 * @since 2023-03-07 09:20:54
 */
@Api(description = "用户签到明细")
@RestController
@RequestMapping("account/uSigndetail")
public class USigndetailController {
    /**
     * 服务对象
     */
    @Resource
    private USigndetailService uSigndetailService;

    /**
     * 分页查询
     *
     * @param uSigndetail 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<USigndetail>> queryByPage(USigndetail uSigndetail, PageRequest pageRequest) {
        return ResponseEntity.ok(this.uSigndetailService.queryByPage(uSigndetail, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<USigndetail> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.uSigndetailService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param uSigndetail 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<USigndetail> add(USigndetail uSigndetail) {
        return ResponseEntity.ok(this.uSigndetailService.insert(uSigndetail));
    }

    /**
     * 编辑数据
     *
     * @param uSigndetail 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<USigndetail> edit(USigndetail uSigndetail) {
        return ResponseEntity.ok(this.uSigndetailService.update(uSigndetail));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.uSigndetailService.deleteById(id));
    }

}

