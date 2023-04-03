package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.bean.PageBean;
import cn.zealon.readingcloud.common.pojo.xzwresources.Composition;
import cn.zealon.readingcloud.book.service.CompositionService;
import cn.zealon.readingcloud.common.result.Result;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 作文表(Composition)表控制层
 *
 * @author makejava
 * @since 2023-03-01 11:14:00
 */
@Api(description = "作文查询接口")
@RestController
@RequestMapping("book/composition")
public class CompositionController {
    /**
     * 服务对象
     */
    @Resource
    private CompositionService compositionService;

    @PostMapping("/pageQuery")
    public ResponseEntity<PageBean<Composition>> pageQuery(@RequestBody JSONObject jsonObject) {

        return ResponseEntity.ok(this.compositionService.pageQuery(jsonObject));

    }

    /**
     * 分页查询
     *
     * @param composition 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<Composition>> queryByPage(Composition composition, PageRequest pageRequest) {
        return ResponseEntity.ok(this.compositionService.queryByPage(composition, pageRequest));
    }

    @GetMapping("queryAll")
    public List<Composition>queryAll(Composition composition){
        List<Composition> list = this.compositionService.queryAll(composition);
        for (Composition cc:list){
            if (cc.getCSynopsis()==null){
                this.compositionService.compositionQRCode(cc.getId());
            }
        }
        return this.compositionService.queryAll(composition);
    }

    //检阅作文数据
//    @GetMapping("/queryContent")
    public List<Map<String,String>>queryContent(){
        return this.compositionService.queryContent();
    }

    @GetMapping("compositionQRCode")
    public Composition compositionQRCode(Long compositionId){
        return this.compositionService.compositionQRCode(compositionId);
    }
    @GetMapping("queryRandoms")
    public List<Composition>queryRandoms(int size){
        return this.compositionService.queryRandoms(size);
    }

    @GetMapping("queryByUserId")
    public List<Composition>queryByUserId(Long userId){
        Composition composition =new Composition();
        composition.setIsused(0);
        composition.setCAuthorid(userId);
        return this.compositionService.queryAll(composition);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Composition> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.compositionService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param composition 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<Composition> add(Composition composition) {
        return ResponseEntity.ok(this.compositionService.insert(composition));
    }

    /**
     * 编辑数据
     *
     * @param composition 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<Composition> edit(Composition composition) {
        return ResponseEntity.ok(this.compositionService.update(composition));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.compositionService.deleteById(id));
    }

}

