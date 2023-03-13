package cn.zealon.readingcloud.account.controller;


import cn.zealon.readingcloud.account.common.utils.HttpRequest;
import cn.zealon.readingcloud.common.cache.RedisService;
import cn.zealon.readingcloud.common.pojo.xzwusers.XzwUser;
import cn.zealon.readingcloud.account.service.XzwUserService;
import cn.zealon.readingcloud.common.result.Result;

import com.alibaba.fastjson.JSONObject;
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
 * 用户表(XzwUser)表控制层
 *
 * @author makejava
 * @since 2023-03-01 10:43:57
 */
@Api(description = "用户接口")
@RestController
@RequestMapping("account/xzwUser")
public class XzwUserController {

    /**
     * 服务对象
     */
    @Resource
    private XzwUserService xzwUserService;

    @Resource
    private RedisService redisService;

    /**
     * 手机验证码登录
     * @param phoneNumber
     * @return
     */
    @GetMapping("/send4Login")
    public Result send4Login(String phoneNumber){
        return this.xzwUserService.send4Login(phoneNumber);
    }

    @GetMapping("/loginCode")
    public Result loginCode(String phoneNumber,String validateCode){
        return this.xzwUserService.loginByPhoneNumber(phoneNumber, validateCode);
    }



    /**
     * 微信openID登录
     *
     * **/
    @PostMapping("/getOpenId")//获取openId
    public String getOpeId(@RequestBody JSONObject js_code) {
        String code = js_code.getString("js_code");
        // 小程序唯一标识 (在微信小程序管理后台获取)
        String appid = "";
        // 小程序的 app secret (在微信小程序管理后台获取)
        String secret = "";
        // 授权（必填）
        String grant_type = "authorization_code";
        // 向微信服务器 使用登录凭证 code 获取 session_key 和 openid
        // 请求参数
        String params = "appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=" + grant_type;
        // 发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        // 解析相应内容（转换成json对象）
        System.out.println(sr);
        JSONObject json = JSONObject.parseObject(sr);
        // 获取会话密钥（session_key）
//        String session_key = json.get("session_key").toString();
        // 用户的唯一标识（openid）
        String openid = (String) json.get("openid");
        return openid;
    }
    @GetMapping("/weChatLogin")
    public Result weChatLogin(String openId,String nickName, String avatarUrl ) {
        return this.xzwUserService.loginByOpenId(openId,nickName,avatarUrl);
    }


//    @PostMapping("/getPhoneNum")//获取手机号
    public Object getPhoneNum(@RequestBody JSONObject js_code){
        // 获取token
        // 小程序唯一标识 (在微信小程序管理后台获取)
        String appid = "";
        // 小程序的 app secret (在微信小程序管理后台获取)
        String secret = "";
        // 授权（必填）
        String grant_type = "client_credential";
        //向微信服务器 使用登录凭证 code 获取 session_key 和 openid
        // 请求参数
        String params2 = "appid=" + appid + "&secret=" + secret + "&grant_type=" + grant_type;
        // 发送请求
        String sr2 = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token", params2);
        // 解析相应内容（转换成json对象）
        JSONObject json2 = JSONObject.parseObject(sr2);
        String access_token = json2.getString("access_token");
        //使用获取到的token和接受到的code像微信获取手机号
        String code = js_code.getString("js_code");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",code);
        String url = ("https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token="+access_token);
        String sr3 = HttpRequest.sendPost(url,jsonObject);
        JSONObject phone_info = JSONObject.parseObject(sr3);
        return phone_info;
    }



//    @ApiOperation("修改头像")
//    @PostMapping(value = "/updateAvatar")
    /*public ResponseEntity<Object> updateUserAvatar(MultipartFile avatar, XzwUser user){

        return new ResponseEntity<>(xzwUserService.updateAvatar(avatar,user), HttpStatus.OK);
    }*/





    /**
     * 分页查询
     *
     * @param xzwUser     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<XzwUser>> queryByPage(XzwUser xzwUser, PageRequest pageRequest) {
        return ResponseEntity.ok(this.xzwUserService.queryByPage(xzwUser, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<XzwUser> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.xzwUserService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param xzwUser 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<XzwUser> add(XzwUser xzwUser) {
        return ResponseEntity.ok(this.xzwUserService.insert(xzwUser));
    }

    /**
     * 编辑数据
     *
     * @param xzwUser 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<XzwUser> edit(XzwUser xzwUser) {
        return ResponseEntity.ok(this.xzwUserService.update(xzwUser));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.xzwUserService.deleteById(id));
    }

}

