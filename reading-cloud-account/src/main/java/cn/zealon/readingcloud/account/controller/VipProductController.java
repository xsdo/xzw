package cn.zealon.readingcloud.account.controller;

import cn.zealon.readingcloud.common.pojo.xzwusers.VipProduct;
import cn.zealon.readingcloud.account.service.VipProductService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 会员商品表(VipProduct)表控制层
 *
 * @author makejava
 * @since 2023-04-26 15:58:15
 */
@Api(description = "会员商品接口")
@RestController
@RequestMapping("account/vipProduct")
public class VipProductController {
    /**
     * 服务对象
     */
    @Resource
    private VipProductService vipProductService;

    /**
     * 分页查询
     *
     * @param vipProduct  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<VipProduct>> queryByPage(VipProduct vipProduct, PageRequest pageRequest) {
        return ResponseEntity.ok(this.vipProductService.queryByPage(vipProduct, pageRequest));
    }

    @GetMapping("queryVip")
    public List<VipProduct> queryVip(){
        VipProduct vipProduct = new VipProduct();
        vipProduct.setIsused(0);
        return this.vipProductService.queryAll(vipProduct);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
//    @GetMapping("{id}")
    public ResponseEntity<VipProduct> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.vipProductService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param vipProduct 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<VipProduct> add(VipProduct vipProduct) {
        return ResponseEntity.ok(this.vipProductService.insert(vipProduct));
    }

    /**
     * 编辑数据
     *
     * @param vipProduct 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<VipProduct> edit(VipProduct vipProduct) {
        return ResponseEntity.ok(this.vipProductService.update(vipProduct));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.vipProductService.deleteById(id));
    }

}

