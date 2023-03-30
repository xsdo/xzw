package cn.zealon.readingcloud.account.controller;

import cn.zealon.readingcloud.account.service.*;
import cn.zealon.readingcloud.common.pojo.xzwusers.*;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.security.pkcs11.wrapper.Constants;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 签到表(USign)表控制层
 *
 * @author makejava
 * @since 2023-03-07 09:20:53
 */
@Api(description = "用户签到接口")
@RestController
@RequestMapping("account/uSign")
public class USignController {
    /**
     * 服务对象
     */
    @Resource
    private USignService uSignService;

    @Resource
    private USigndetailService uSigndetailService;

    @Resource
    private UAttributeService uAttributeService;

    @Resource
    private XzwUserService xzwUserService;

    @Resource
    private UFlowersService uFlowersService;

    @GetMapping("/sign")
    public JSONObject sign (Long userId){
        return this.uSignService.sign(userId);
    }
    @GetMapping("/getSign")
    public Map<String,Object> getSign(Long userId) {
        return this.uSignService.getSign(userId);
    }

    /**
     * 分页查询
     *
     * @param uSign       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<USign>> queryByPage(USign uSign, PageRequest pageRequest) {
        return ResponseEntity.ok(this.uSignService.queryByPage(uSign, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
//    @GetMapping("{id}")
    public ResponseEntity<USign> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.uSignService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param uSign 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<USign> add(USign uSign) {
        return ResponseEntity.ok(this.uSignService.insert(uSign));
    }

    /**
     * 编辑数据
     *
     * @param uSign 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<USign> edit(USign uSign) {
        return ResponseEntity.ok(this.uSignService.update(uSign));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.uSignService.deleteById(id));
    }

}

