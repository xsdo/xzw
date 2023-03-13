package cn.zealon.readingcloud.account.controller;

import cn.zealon.readingcloud.common.pojo.xzwusers.UActivation;
import cn.zealon.readingcloud.account.service.UActivationService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 激活码表(UActivation)表控制层
 *
 * @author makejava
 * @since 2023-03-01 10:43:51
 */
@Api(description = "激活码接口")
@RestController
@RequestMapping("account/uActivation")
public class UActivationController {
    /**
     * 服务对象
     */
    @Resource
    private UActivationService uActivationService;

    /**
     * 分页查询
     *
     * @param uActivation 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<UActivation>> queryByPage(UActivation uActivation, PageRequest pageRequest) {
        return ResponseEntity.ok(this.uActivationService.queryByPage(uActivation, pageRequest));
    }

    @GetMapping("queryAll")
    public List<UActivation>queryAll(UActivation uActivation){
        return this.uActivationService.queryAll(uActivation);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<UActivation> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.uActivationService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param uActivation 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<UActivation> add(UActivation uActivation) {
        return ResponseEntity.ok(this.uActivationService.insert(uActivation));
    }

    /**
     * 编辑数据
     *
     * @param uActivation 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<UActivation> edit(UActivation uActivation) {
        return ResponseEntity.ok(this.uActivationService.update(uActivation));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.uActivationService.deleteById(id));
    }

}

