package cn.zealon.readingcloud.account.controller;

import cn.zealon.readingcloud.common.pojo.xzwusers.WebLogin;
import cn.zealon.readingcloud.account.service.WebLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 扫码登录表(WebLogin)表控制层
 *
 * @author makejava
 * @since 2023-04-18 13:38:36
 */
@Api(description = "扫码登录接口")
@RestController
@RequestMapping("account/webLogin")
public class WebLoginController {
    /**
     * 服务对象
     */
    @Resource
    private WebLoginService webLoginService;

    /**
     * 分页查询
     *
     * @param webLogin    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<WebLogin>> queryByPage(WebLogin webLogin, PageRequest pageRequest) {
        return ResponseEntity.ok(this.webLoginService.queryByPage(webLogin, pageRequest));
    }

    @ApiOperation(value = "web轮询状态", httpMethod = "GET")
    @GetMapping("queryLoginStatus")
    public WebLogin queryByUuid(String uuid){
        return this.webLoginService.queryByUuid(uuid);
    }

    @ApiOperation(value = "获取登录二维码", httpMethod = "GET")
    @GetMapping("queryQrCodeStatus")
    public WebLogin queryQrCodeStatus(String uuid){
        return this.webLoginService.queryQrCodeStatus(uuid);
    }


    @ApiOperation(value = "用户确认登录", httpMethod = "GET")
    @GetMapping("loginWeb")
    public String loginWeb(String uuid,Long userId){
        return this.webLoginService.loginWeb(uuid, userId);
    }

    @ApiOperation(value = "用户打开二维码", httpMethod = "GET")
    @GetMapping("changeLoginStatus")
    public void changeLoginStatus(String uuid){
        this.webLoginService.changeLoginStatus(uuid);
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
//    @GetMapping("{id}")
    public ResponseEntity<WebLogin> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.webLoginService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param webLogin 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<WebLogin> add(WebLogin webLogin) {
        return ResponseEntity.ok(this.webLoginService.insert(webLogin));
    }

    /**
     * 编辑数据
     *
     * @param webLogin 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<WebLogin> edit(WebLogin webLogin) {
        return ResponseEntity.ok(this.webLoginService.update(webLogin));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.webLoginService.deleteById(id));
    }

}

