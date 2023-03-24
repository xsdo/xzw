package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.CAuthors;
import cn.zealon.readingcloud.book.service.CAuthorsService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * 作者表(CAuthors)表控制层
 *
 * @author makejava
 * @since 2023-03-01 11:14:02
 */
@Api(description = "作者接口")
@RestController
@RequestMapping("book/cAuthors")
public class CAuthorsController {
    /**
     * 服务对象
     */
    @Resource
    private CAuthorsService cAuthorsService;

    /**
     * 分页查询
     *
     * @param cAuthors    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<CAuthors>> queryByPage(CAuthors cAuthors, PageRequest pageRequest) {
        return ResponseEntity.ok(this.cAuthorsService.queryByPage(cAuthors, pageRequest));
    }

    @GetMapping("queryAll")
    public List<CAuthors>queryAll(CAuthors cAuthors){
        return this.cAuthorsService.queryAll(cAuthors);
    }


    @GetMapping("toAuthors")
    public JSONObject toAuthors(String title, String name, String province, String city, String area, String school  ){
        return this.cAuthorsService.toAuthors(title, name, province, city, area, school);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<CAuthors> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.cAuthorsService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param cAuthors 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<CAuthors> add(CAuthors cAuthors) {
        return ResponseEntity.ok(this.cAuthorsService.insert(cAuthors));
    }

    /**
     * 编辑数据
     *
     * @param cAuthors 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<CAuthors> edit(CAuthors cAuthors) {
        return ResponseEntity.ok(this.cAuthorsService.update(cAuthors));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.cAuthorsService.deleteById(id));
    }

}

