package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.ReadEssay;
import cn.zealon.readingcloud.book.service.ReadEssayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 阅读文章表(ReadEssay)表控制层
 *
 * @author makejava
 * @since 2023-04-17 09:28:14
 */
@Api(description = "阅读文章接口")
@RestController
@RequestMapping("book/readEssay")
public class ReadEssayController {
    /**
     * 服务对象
     */
    @Resource
    private ReadEssayService readEssayService;

    /**
     * 分页查询
     *
     * @param readEssay   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<ReadEssay>> queryByPage(ReadEssay readEssay, PageRequest pageRequest) {
        return ResponseEntity.ok(this.readEssayService.queryByPage(readEssay, pageRequest));
    }

    @GetMapping("queryAll")
    public List<ReadEssay> queryAll(ReadEssay readEssay){
        return this.readEssayService.queryAll(readEssay);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ReadEssay> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.readEssayService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param readEssay 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<ReadEssay> add(ReadEssay readEssay) {
        return ResponseEntity.ok(this.readEssayService.insert(readEssay));
    }

    @ApiOperation(value = "录入文章内容（所属图书id，标题，内容，编号）")
//    @GetMapping("insertReadEssay")
    public ResponseEntity<ReadEssay> insertReadEssay(Long pid ,String title ,String content ,Integer number) {
        ReadEssay readEssay=new ReadEssay();
        readEssay.setIsused(0);
        readEssay.setCreateTime(new Date());
        readEssay.setUpdateTime(new Date());
        readEssay.setPid(pid);
        readEssay.setTitle(title);
        readEssay.setContent(content);
        readEssay.setNumber(number);
        return ResponseEntity.ok(this.readEssayService.insert(readEssay));
    }

    /**
     * 编辑数据
     *
     * @param readEssay 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<ReadEssay> edit(ReadEssay readEssay) {
        return ResponseEntity.ok(this.readEssayService.update(readEssay));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.readEssayService.deleteById(id));
    }

}

