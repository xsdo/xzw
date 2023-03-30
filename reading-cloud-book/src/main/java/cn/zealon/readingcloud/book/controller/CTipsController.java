package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.CTips;
import cn.zealon.readingcloud.book.service.CTipsService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 举报表(CTips)表控制层
 *
 * @author makejava
 * @since 2023-03-01 11:13:58
 */
@Api(description = "举报接口")
@RestController
@RequestMapping("book/cTips")
public class CTipsController {
    /**
     * 服务对象
     */
    @Resource
    private CTipsService cTipsService;

    /**
     * 分页查询
     *
     * @param cTips       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<CTips>> queryByPage(CTips cTips, PageRequest pageRequest) {
        return ResponseEntity.ok(this.cTipsService.queryByPage(cTips, pageRequest));
    }

    @GetMapping("queryAll")
    public List<CTips>queryAll(CTips cTips){
        return this.cTipsService.queryAll(cTips);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<CTips> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.cTipsService.queryById(id));
    }

    @GetMapping("toTips")
    public JSONObject toTips(Long discussId,Long discussUserId,Long tipsUserId,String cause){
        return this.cTipsService.toTips(discussId, discussUserId,tipsUserId, cause);
    }
    /**
     * 新增数据
     *
     * @param cTips 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<CTips> add(CTips cTips) {
        return ResponseEntity.ok(this.cTipsService.insert(cTips));
    }

    /**
     * 编辑数据
     *
     * @param cTips 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<CTips> edit(CTips cTips) {
        return ResponseEntity.ok(this.cTipsService.update(cTips));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.cTipsService.deleteById(id));
    }

}

