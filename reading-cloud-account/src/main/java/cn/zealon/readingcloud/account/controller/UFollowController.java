package cn.zealon.readingcloud.account.controller;

import cn.zealon.readingcloud.account.service.UAttributeService;
import cn.zealon.readingcloud.common.pojo.xzwusers.UAttribute;
import cn.zealon.readingcloud.common.pojo.xzwusers.UFollow;
import cn.zealon.readingcloud.account.service.UFollowService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户关注表(UFollow)表控制层
 *
 * @author makejava
 * @since 2023-03-01 10:43:54
 */
@Api(description = "用户关注接口")
@RestController
@RequestMapping("account/uFollow")
public class UFollowController {
    /**
     * 服务对象
     */
    @Resource
    private UFollowService uFollowService;

    @Resource
    private UAttributeService uAttributeService;


    /**
     * 分页查询
     *
     * @param uFollow     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<UFollow>> queryByPage(UFollow uFollow, PageRequest pageRequest) {
        return ResponseEntity.ok(this.uFollowService.queryByPage(uFollow, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<UFollow> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.uFollowService.queryById(id));
    }

    //查询用户的关注
    @GetMapping("queryFollows")
    public List<UFollow>queryFollows(Long userId){
        return this.uFollowService.queryFollow(userId);
    }
    //查询是否关注
    @GetMapping("checkFollow")
    public Integer checkFollow(Long userId, Long followId){
        return this.uFollowService.checkFollow(userId, followId);
    }
    //关注
    @GetMapping("doFollow")
    public JSONObject doFollow(Long userId, Long followId){
        return this.uFollowService.doFollow(userId, followId);
    }
    //查询所有关注
    @GetMapping("queryFollow")
    public JSONObject queryFollow(Long userId){
        return this.uFollowService.queryFollowAll(userId);
    }
    //查询所有粉丝
    @GetMapping("queryFans")
    public JSONObject queryFans(Long followId){
        return this.uFollowService.queryFansAll(followId);
    }
    /**
     * 新增数据
     *
     * @param uFollow 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<UFollow> add(UFollow uFollow) {
        return ResponseEntity.ok(this.uFollowService.insert(uFollow));
    }

    /**
     * 编辑数据
     *
     * @param uFollow 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<UFollow> edit(UFollow uFollow) {
        return ResponseEntity.ok(this.uFollowService.update(uFollow));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.uFollowService.deleteById(id));
    }

}

