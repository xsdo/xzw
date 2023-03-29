package cn.zealon.readingcloud.account.controller;

import cn.zealon.readingcloud.account.service.UNoticeService;
import cn.zealon.readingcloud.common.pojo.xzwusers.UNotice;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 消息通知表(UNotice)表控制层
 *
 * @author makejava
 * @since 2023-03-09 17:33:38
 */
@Api(description = "消息通知接口")
@RestController
@RequestMapping("account/uNotice")
public class UNoticeController {
    /**
     * 服务对象
     */
    @Resource
    private UNoticeService uNoticeService;

    /**
     * 分页查询
     *
     * @param uNotice     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<UNotice>> queryByPage(UNotice uNotice, PageRequest pageRequest) {
        return ResponseEntity.ok(this.uNoticeService.queryByPage(uNotice, pageRequest));
    }

    @GetMapping("queryAll")
    public List<UNotice>queryAll(UNotice uNotice){
        return this.uNoticeService.queryAll(uNotice);
    }

    @GetMapping("queryByUserId")
    public List<UNotice>queryByUserId(Long userId){
        UNotice uNotice =new UNotice();
        uNotice.setIsused(0);
        uNotice.setUserId(userId);
        return this.uNoticeService.queryAll(uNotice);
    }
    @GetMapping("countNotice")
    public long countNotice(Long userId){
        return this.uNoticeService.countNotice(userId);
    }

    @GetMapping("readNotice")
    public List<UNotice>readNotice(Long userId){
    return this.uNoticeService.readNotice(userId);
    }

    @GetMapping("doNotice")
    public void doNotice(Long userId,String name,int type,String coment){
        this.uNoticeService.doNotice(userId, name, type, coment);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<UNotice> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.uNoticeService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param uNotice 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<UNotice> add(UNotice uNotice) {
        return ResponseEntity.ok(this.uNoticeService.insert(uNotice));
    }

    /**
     * 编辑数据
     *
     * @param uNotice 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<UNotice> edit(UNotice uNotice) {
        return ResponseEntity.ok(this.uNoticeService.update(uNotice));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.uNoticeService.deleteById(id));
    }

}

