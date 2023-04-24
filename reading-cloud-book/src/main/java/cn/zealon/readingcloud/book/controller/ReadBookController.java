package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.ReadBook;
import cn.zealon.readingcloud.book.service.ReadBookService;
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
 * 阅读图书表(ReadBook)表控制层
 *
 * @author makejava
 * @since 2023-04-17 09:28:15
 */
@Api(description = "阅读图书接口")
@RestController
@RequestMapping("book/readBook")
public class ReadBookController {
    /**
     * 服务对象
     */
    @Resource
    private ReadBookService readBookService;

    /**
     * 分页查询
     *
     * @param readBook    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<ReadBook>> queryByPage(ReadBook readBook, PageRequest pageRequest) {
        return ResponseEntity.ok(this.readBookService.queryByPage(readBook, pageRequest));
    }

    @GetMapping("queryAll")
    public List<ReadBook> queryAll(ReadBook readBook){
        return this.readBookService.queryAll(readBook);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ReadBook> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.readBookService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param readBook 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<ReadBook> add(ReadBook readBook) {
        return ResponseEntity.ok(this.readBookService.insert(readBook));
    }

    @ApiOperation(value = "录入书籍信息（书名，封面，简介，出版社，序图）")
//    @GetMapping("insertReadBook")
    public ResponseEntity<ReadBook> insertReadBook(String name,String image,String profile,String press,String diagram) {
        ReadBook readBook=new ReadBook();
        readBook.setIsused(0);
        readBook.setCreateTime(new Date());
        readBook.setUpdateTime(new Date());
        readBook.setName(name);
        readBook.setImage(image);
        readBook.setPress(profile);
        readBook.setPress(press);
        readBook.setDiagram(diagram);
        return ResponseEntity.ok(this.readBookService.insert(readBook));
    }

    /**
     * 编辑数据
     *
     * @param readBook 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<ReadBook> edit(ReadBook readBook) {
        return ResponseEntity.ok(this.readBookService.update(readBook));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.readBookService.deleteById(id));
    }

}

