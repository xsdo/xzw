package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.Magazines;
import cn.zealon.readingcloud.book.service.MagazinesService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 杂志栏目表(Magazines)表控制层
 *
 * @author makejava
 * @since 2023-03-15 17:17:09
 */
@Api(description = "杂志栏目接口")
@RestController
@RequestMapping("book/magazines")
public class MagazinesController {
    /**
     * 服务对象
     */
    @Resource
    private MagazinesService magazinesService;

    /**
     * 分页查询
     *
     * @param magazines   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Magazines>> queryByPage(Magazines magazines, PageRequest pageRequest) {
        return ResponseEntity.ok(this.magazinesService.queryByPage(magazines, pageRequest));
    }

    @GetMapping("queryAll")
    public List<Magazines> queryAll(int type){
        Magazines magazines=new Magazines();
        magazines.setIsused(0);
        magazines.setType(type);
        return this.magazinesService.queryAll(magazines);
    }
    @GetMapping("queryAllTry")
    public List<Magazines>queryAllTry(){
        Magazines magazines=new Magazines();
        magazines.setIsused(0);
        magazines.setTryvip(1);
        return this.magazinesService.queryAll(magazines);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Magazines> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.magazinesService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param magazines 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Magazines> add(Magazines magazines) {
        return ResponseEntity.ok(this.magazinesService.insert(magazines));
    }

    /**
     * 编辑数据
     *
     * @param magazines 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Magazines> edit(Magazines magazines) {
        return ResponseEntity.ok(this.magazinesService.update(magazines));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.magazinesService.deleteById(id));
    }

}

