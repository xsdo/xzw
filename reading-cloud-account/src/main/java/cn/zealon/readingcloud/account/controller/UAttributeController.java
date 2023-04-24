package cn.zealon.readingcloud.account.controller;

import cn.zealon.readingcloud.account.service.WxService;
import cn.zealon.readingcloud.common.pojo.xzwusers.UAttribute;
import cn.zealon.readingcloud.account.service.UAttributeService;
import cn.zealon.readingcloud.common.result.Result;
import cn.zealon.readingcloud.common.result.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户属性表(UAttribute)表控制层
 *
 * @author makejava
 * @since 2023-03-06 09:23:20
 */
@Api(description = "用户属性接口")
@RestController
@RequestMapping("account/uAttribute")
public class UAttributeController {
    /**
     * 服务对象
     */
    @Resource
    private UAttributeService uAttributeService;
    @Resource
    private WxService wxService;

    @ApiOperation("上传头像")
    @PostMapping(value = "/updateHead")
    public ResponseEntity<Object> updateHead(MultipartFile avatar){
        if (wxService.checkImg(avatar)){return null;}
        return new ResponseEntity<>(uAttributeService.updateHeadImg(avatar), HttpStatus.OK);
    }
    @ApiOperation("修改头像")
    @GetMapping(value = "/changeHead")
    public ResponseEntity<Object> changeHead(Long userId,String fileUrl){
        return new ResponseEntity<>(uAttributeService.changeHead(userId, fileUrl), HttpStatus.OK);
    }

    @ApiOperation("上传背景")
    @PostMapping(value = "/updateBackGround")
    public ResponseEntity<Object> updateBackGround(MultipartFile avatar){
        if (wxService.checkImg(avatar)){return null;}
        return new ResponseEntity<>(uAttributeService.updateBackGround(avatar), HttpStatus.OK);
    }
    @ApiOperation("修改背景")
    @GetMapping(value = "/changeBackGround")
    public ResponseEntity<Object> changeBackGround(Long userId,String fileUrl){
        return new ResponseEntity<>(uAttributeService.changeBackGround(userId, fileUrl), HttpStatus.OK);
    }



    @ApiOperation("修改资料")
    @PostMapping(value = "/change")
    public ResponseEntity<Object> change(@RequestBody UAttribute uAttribute){
        if (wxService.checkText(uAttribute.getQqnum())){return null;}
        if (wxService.checkText(uAttribute.getSign())){return null;}
        return new ResponseEntity<>(uAttributeService.update(uAttribute), HttpStatus.OK);
    }
    /**
     * 分页查询
     *
     * @param uAttribute  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<UAttribute>> queryByPage(UAttribute uAttribute, PageRequest pageRequest) {
        return ResponseEntity.ok(this.uAttributeService.queryByPage(uAttribute, pageRequest));
    }

    @GetMapping("queryAll")
    public List<UAttribute>queryAll(UAttribute uAttribute){
        return this.uAttributeService.queryAll(uAttribute);
    }

    @GetMapping("queryRand")
    public List<UAttribute>queryRand(int size){
        return this.uAttributeService.queryRand(size);
    }


    @GetMapping("queryByUserId")
    public UAttribute queryByUserId(@RequestParam("userId")Long userId) {
        return this.uAttributeService.queryById(userId);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<UAttribute> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.uAttributeService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param uAttribute 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<UAttribute> add(UAttribute uAttribute) {
        return ResponseEntity.ok(this.uAttributeService.insert(uAttribute));
    }

    /**
     * 编辑数据
     *
     * @param uAttribute 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<UAttribute> edit(UAttribute uAttribute) {
        return ResponseEntity.ok(this.uAttributeService.update(uAttribute));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.uAttributeService.deleteById(id));
    }

}

