package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.account.feign.client.UserAttributeClient;
import cn.zealon.readingcloud.book.service.CDiscussService;
import cn.zealon.readingcloud.book.vo.DiscussComVO;
import cn.zealon.readingcloud.book.vo.DiscussUserVO;
import cn.zealon.readingcloud.common.pojo.xzwresources.CDiscuss;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评论表(CDiscuss)表控制层
 *
 * @author makejava
 * @since 2023-03-01 11:13:54
 */
@Api(description = "评论接口")
@RestController
@RequestMapping("book/cDiscuss")
public class CDiscussController {
    /**
     * 服务对象
     */
    @Resource
    private CDiscussService cDiscussService;
    @Resource
    private UserAttributeClient userAttributeClient;
    /**
     * 分页查询
     *
     * @param cDiscuss    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<CDiscuss>> queryByPage(CDiscuss cDiscuss, PageRequest pageRequest) {
        return ResponseEntity.ok(this.cDiscussService.queryByPage(cDiscuss, pageRequest));
    }

    @GetMapping("queryAll")
    public List<CDiscuss>queryAll(CDiscuss cDiscuss){
        return this.cDiscussService.queryAll(cDiscuss);
    }


    @GetMapping("queryByUserId")
    public List<DiscussComVO>queryByUserId(Long userId){
        return this.cDiscussService.queryByUserId(userId);
    }
    @GetMapping("queryByCompositionId")
    public List<DiscussUserVO>queryByCompositionId(Long compositionId,String remarks){
        return this.cDiscussService.queryByCompositionId(compositionId,remarks);
    }

    @GetMapping("doDiscuss")
    public JSONObject doDiscuss(Long userId,String discuss,Long compositionId,Integer type){
        return this.cDiscussService.doDiscuss(userId, discuss, compositionId,type);
    }
    //type 0文章评论  1随笔评论 2圈子评论

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<CDiscuss> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.cDiscussService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param cDiscuss 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<CDiscuss> add(CDiscuss cDiscuss) {
        return ResponseEntity.ok(this.cDiscussService.insert(cDiscuss));
    }

    /**
     * 编辑数据
     *
     * @param cDiscuss 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<CDiscuss> edit(CDiscuss cDiscuss) {
        return ResponseEntity.ok(this.cDiscussService.update(cDiscuss));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.cDiscussService.deleteById(id));
    }

}

