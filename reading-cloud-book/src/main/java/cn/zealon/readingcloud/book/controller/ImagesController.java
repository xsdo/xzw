package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.book.service.WxService;
import cn.zealon.readingcloud.common.pojo.xzwresources.Images;
import cn.zealon.readingcloud.book.service.ImagesService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 随机图表(Images)表控制层
 *
 * @author makejava
 * @since 2023-03-22 18:22:34
 */
@Api(description = "随机图接口")
@RestController
@RequestMapping("book/images")
public class ImagesController {
    /**
     * 服务对象
     */
    @Resource
    private ImagesService imagesService;

    @Resource
    private WxService wxService;

    /**
     * 分页查询
     *
     * @param images      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<Images>> queryByPage(Images images, PageRequest pageRequest) {
        return ResponseEntity.ok(this.imagesService.queryByPage(images, pageRequest));
    }


    @PostMapping("updateImageTwo")
    public Map<String, String> updateImageTwo(MultipartFile big, MultipartFile small ){
        if (wxService.checkImg(big)){return null;}
        if (wxService.checkImg(small)){return null;}
        return imagesService.updateImageTwo(big,small);
    }
    @PostMapping("updateImage")
    public Map<String, String> updateImage(MultipartFile big){
        if (wxService.checkImg(big)){return null;}
        return imagesService.updateImage(big);
    }
    @GetMapping("queryRand")
    public Images queryRand(){
        return this.imagesService.queryRand();
    }

    @GetMapping("queryRandTwo")
    public Images queryRandTwo(){
        return this.imagesService.queryRandTwo();
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
//    @GetMapping("{id}")
    public ResponseEntity<Images> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.imagesService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param images 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<Images> add(Images images) {
        return ResponseEntity.ok(this.imagesService.insert(images));
    }

    /**
     * 编辑数据
     *
     * @param images 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<Images> edit(Images images) {
        return ResponseEntity.ok(this.imagesService.update(images));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.imagesService.deleteById(id));
    }

}

