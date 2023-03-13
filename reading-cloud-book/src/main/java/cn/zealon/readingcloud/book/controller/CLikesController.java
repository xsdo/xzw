package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.CLikes;
import cn.zealon.readingcloud.book.service.CLikesService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户点赞表(CLikes)表控制层
 *
 * @author makejava
 * @since 2023-03-09 10:51:19
 */
@Api(description = "点赞接口")
@RestController
@RequestMapping("book/cLikes")
public class CLikesController {
    /**
     * 服务对象
     */
    @Resource
    private CLikesService cLikesService;

    /**
     * 分页查询
     *
     * @param cLikes      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<CLikes>> queryByPage(CLikes cLikes, PageRequest pageRequest) {
        return ResponseEntity.ok(this.cLikesService.queryByPage(cLikes, pageRequest));
    }

    //查询是否点赞
    @GetMapping("checkLikes")
    public Integer checkLikes(Long userId ,Long likesId){
        return this.cLikesService.checkLikes(userId, likesId);
    }
    //点赞
    @GetMapping("toLikes")
    public JSONObject toLikes (Long userId ,Long likesId,Integer type){
        return this.cLikesService.toLikes(userId, likesId, type);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<CLikes> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.cLikesService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param cLikes 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<CLikes> add(CLikes cLikes) {
        return ResponseEntity.ok(this.cLikesService.insert(cLikes));
    }

    /**
     * 编辑数据
     *
     * @param cLikes 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<CLikes> edit(CLikes cLikes) {
        return ResponseEntity.ok(this.cLikesService.update(cLikes));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.cLikesService.deleteById(id));
    }

}

