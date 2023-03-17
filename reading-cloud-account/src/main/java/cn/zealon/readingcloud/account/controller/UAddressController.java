package cn.zealon.readingcloud.account.controller;

import cn.zealon.readingcloud.common.pojo.xzwusers.UAddress;
import cn.zealon.readingcloud.account.service.UAddressService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户地址表(UAddress)表控制层
 *
 * @author makejava
 * @since 2023-03-10 17:54:33
 */
@Api(description = "地址接口")
@RestController
@RequestMapping("account/uAddress")
public class UAddressController {
    /**
     * 服务对象
     */
    @Resource
    private UAddressService uAddressService;

    /**
     * 分页查询
     *
     * @param uAddress    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<UAddress>> queryByPage(UAddress uAddress, PageRequest pageRequest) {
        return ResponseEntity.ok(this.uAddressService.queryByPage(uAddress, pageRequest));
    }

    @GetMapping("queryAll")
    public List<UAddress>queryAll(UAddress uAddress){
        return this.uAddressService.queryAll(uAddress);
    }

    @GetMapping("queryByUserId")
    public UAddress queryByUserId(Long userId){
        return this.uAddressService.queryByUserId(userId);
    }


    @GetMapping("addAddress")
    public UAddress addAddress(Long userId,String name,String phoneNumber,String address){
        return this.uAddressService.addAddress(userId, name, phoneNumber, address);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<UAddress> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.uAddressService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param uAddress 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<UAddress> add(UAddress uAddress) {
        return ResponseEntity.ok(this.uAddressService.insert(uAddress));
    }

    /**
     * 编辑数据
     *
     * @param uAddress 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<UAddress> edit(UAddress uAddress) {
        return ResponseEntity.ok(this.uAddressService.update(uAddress));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.uAddressService.deleteById(id));
    }

}

