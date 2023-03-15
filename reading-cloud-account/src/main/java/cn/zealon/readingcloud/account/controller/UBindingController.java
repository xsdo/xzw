package cn.zealon.readingcloud.account.controller;

import cn.zealon.readingcloud.common.pojo.xzwusers.UBinding;
import cn.zealon.readingcloud.account.service.UBindingService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 绑定班级表(UBinding)表控制层
 *
 * @author makejava
 * @since 2023-03-01 10:43:53
 */
@Api(description = "绑定班级接口")
@RestController
@RequestMapping("account/uBinding")
public class UBindingController {
    /**
     * 服务对象
     */
    @Resource
    private UBindingService uBindingService;

    /**
     * 分页查询
     *
     * @param uBinding    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<UBinding>> queryByPage(UBinding uBinding, PageRequest pageRequest) {
        return ResponseEntity.ok(this.uBindingService.queryByPage(uBinding, pageRequest));
    }

    @GetMapping("queryAll")
    public List<UBinding>queryAll(UBinding uBinding){
        return this.uBindingService.queryAll(uBinding);
    }

    @GetMapping("queryByTeacherId")
    public List<UBinding> queryByTeacherId(Long teacherId) {
        return this.uBindingService.queryByTeacherId(teacherId);
    }

    @GetMapping("doBanding")
    public JSONObject doBinding(Long userId, Long teacherId){
        return this.uBindingService.doBinding(userId, teacherId);
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<UBinding> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.uBindingService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param uBinding 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<UBinding> add(UBinding uBinding) {
        return ResponseEntity.ok(this.uBindingService.insert(uBinding));
    }

    /**
     * 编辑数据
     *
     * @param uBinding 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<UBinding> edit(UBinding uBinding) {
        return ResponseEntity.ok(this.uBindingService.update(uBinding));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.uBindingService.deleteById(id));
    }

}

