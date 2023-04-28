package cn.zealon.readingcloud.account.controller;

import cn.zealon.readingcloud.common.pojo.xzwusers.PayInfo;
import cn.zealon.readingcloud.account.service.PayInfoService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 支付信息表(PayInfo)表控制层
 *
 * @author makejava
 * @since 2023-04-26 15:58:13
 */
@Api(description = "支付信息接口")
@RestController
@RequestMapping("account/payInfo")
public class PayInfoController {
    /**
     * 服务对象
     */
    @Resource
    private PayInfoService payInfoService;

    /**
     * 分页查询
     *
     * @param payInfo     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<PayInfo>> queryByPage(PayInfo payInfo, PageRequest pageRequest) {
        return ResponseEntity.ok(this.payInfoService.queryByPage(payInfo, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
//    @GetMapping("{id}")
    public ResponseEntity<PayInfo> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.payInfoService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param payInfo 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<PayInfo> add(PayInfo payInfo) {
        return ResponseEntity.ok(this.payInfoService.insert(payInfo));
    }

    /**
     * 编辑数据
     *
     * @param payInfo 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<PayInfo> edit(PayInfo payInfo) {
        return ResponseEntity.ok(this.payInfoService.update(payInfo));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.payInfoService.deleteById(id));
    }

}

