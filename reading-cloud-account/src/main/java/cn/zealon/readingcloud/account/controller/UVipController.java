package cn.zealon.readingcloud.account.controller;

import cn.zealon.readingcloud.common.pojo.xzwusers.UVip;
import cn.zealon.readingcloud.account.service.UVipService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户会员表(UVip)表控制层
 *
 * @author makejava
 * @since 2023-03-01 10:43:56
 */
@Api(description = "用户会员接口")
@RestController
@RequestMapping("account/uVip")
public class UVipController {
    /**
     * 服务对象
     */
    @Resource
    private UVipService uVipService;

    /**
     * 分页查询
     *
     * @param uVip        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<UVip>> queryByPage(UVip uVip, PageRequest pageRequest) {
        return ResponseEntity.ok(this.uVipService.queryByPage(uVip, pageRequest));
    }

    @GetMapping("queryAll")
    public List<UVip>queryAll(UVip uVip){
        return this.uVipService.queryAll(uVip);
    }

    @GetMapping("getVipInfo")
    public JSONObject getVipInfo(Long userId){
        return this.uVipService.queryVipByUserId(userId);
    }

    @GetMapping("toVipFirst")
    public void toVipFirst(Long userId){
        this.uVipService.toVipFirst(userId);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<UVip> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.uVipService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param uVip 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<UVip> add(UVip uVip) {
        return ResponseEntity.ok(this.uVipService.insert(uVip));
    }

    /**
     * 编辑数据
     *
     * @param uVip 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<UVip> edit(UVip uVip) {
        return ResponseEntity.ok(this.uVipService.update(uVip));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.uVipService.deleteById(id));
    }

}

