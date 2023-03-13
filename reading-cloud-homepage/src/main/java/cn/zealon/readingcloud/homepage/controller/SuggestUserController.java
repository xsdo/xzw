package cn.zealon.readingcloud.homepage.controller;

import cn.zealon.readingcloud.common.pojo.xzwtasks.SuggestUser;
import cn.zealon.readingcloud.homepage.service.SuggestUserService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 推荐用户表(SuggestUser)表控制层
 *
 * @author makejava
 * @since 2023-03-01 11:11:39
 */
@Api(description = "推荐用户")
@RestController
@RequestMapping("index/suggestUser")
public class SuggestUserController {
    /**
     * 服务对象
     */
    @Resource
    private SuggestUserService suggestUserService;

    /**
     * 分页查询
     *
     * @param suggestUser 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<SuggestUser>> queryByPage(SuggestUser suggestUser, PageRequest pageRequest) {
        return ResponseEntity.ok(this.suggestUserService.queryByPage(suggestUser, pageRequest));
    }

    @GetMapping("/queryAll")
    public List<SuggestUser> queryAll(SuggestUser suggestUser){
        return this.suggestUserService.queryAll(suggestUser);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<SuggestUser> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.suggestUserService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param suggestUser 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<SuggestUser> add(SuggestUser suggestUser) {
        return ResponseEntity.ok(this.suggestUserService.insert(suggestUser));
    }

    /**
     * 编辑数据
     *
     * @param suggestUser 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<SuggestUser> edit(SuggestUser suggestUser) {
        return ResponseEntity.ok(this.suggestUserService.update(suggestUser));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.suggestUserService.deleteById(id));
    }

}

