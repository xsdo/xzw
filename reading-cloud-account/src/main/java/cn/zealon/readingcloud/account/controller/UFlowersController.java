package cn.zealon.readingcloud.account.controller;

import cn.zealon.readingcloud.common.pojo.xzwusers.UFlowers;
import cn.zealon.readingcloud.account.service.UFlowersService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 鲜花表(UFlowers)表控制层
 *
 * @author makejava
 * @since 2023-03-06 09:23:22
 */
@Api(description = "积分接口")
@RestController
@RequestMapping("account/uFlowers")
public class UFlowersController {
    /**
     * 服务对象
     */
    @Resource
    private UFlowersService uFlowersService;

    /**
     * 分页查询
     *
     * @param uFlowers    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<UFlowers>> queryByPage(UFlowers uFlowers, PageRequest pageRequest) {
        return ResponseEntity.ok(this.uFlowersService.queryByPage(uFlowers, pageRequest));
    }

//    @GetMapping("queryAll")
    public List<UFlowers>queryAll(UFlowers uFlowers){
        return this.uFlowersService.queryAll(uFlowers);
    }

    @GetMapping("queryByUserId")
    public List<UFlowers>queryByUserId(Long userId){
        return this.uFlowersService.queryByUserId(userId);
    }

    @GetMapping("queryByTeacherId")
    public List<UFlowers>queryByTeacherId(Long teacherId){
        return this.uFlowersService.queryByTeacherId(teacherId);
    }

    @GetMapping("giveFlowers")
    public JSONObject giveFlowers(Long userId,Long teacherId, int flowers){
        return this.uFlowersService.giveFlowers(userId, teacherId, flowers);
    }
    @GetMapping("addFlowers")
    public JSONObject addFlowers(Long userId, int flowers, String remarks){
        return this.uFlowersService.addFlowers(userId,flowers,remarks);
    }



    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
//    @GetMapping("{id}")
    public ResponseEntity<UFlowers> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.uFlowersService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param uFlowers 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<UFlowers> add(UFlowers uFlowers) {
        return ResponseEntity.ok(this.uFlowersService.insert(uFlowers));
    }

    /**
     * 编辑数据
     *
     * @param uFlowers 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<UFlowers> edit(UFlowers uFlowers) {
        return ResponseEntity.ok(this.uFlowersService.update(uFlowers));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.uFlowersService.deleteById(id));
    }

}

