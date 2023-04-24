package cn.zealon.readingcloud.account.service.impl;


import cn.zealon.readingcloud.account.common.config.SmsConfig;
import cn.zealon.readingcloud.account.common.utils.HttpRequestUtil;
import cn.zealon.readingcloud.account.common.utils.WxUtil;
import cn.zealon.readingcloud.account.service.WxService;
import cn.zealon.readingcloud.common.cache.RedisService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.springframework.util.StringUtils.isEmpty;

@Service
public class WxServiceImpl implements WxService {

    @Resource
    private RedisService redisService;

    @Resource
    private SmsConfig smsConfig;
    /**
     * 监测文本内容是否含有敏感信息
     */
    @Override
    public  Boolean checkText(String msg){
        //获取token
        String token = getWxAccessToken();
        //返回监测结果
        return checkMsg(msg, token);
    }

    private  Boolean checkMsg(String msg, String token) {
       return WxUtil.checkMsg(msg, token);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public  Boolean checkImg(MultipartFile multipartFile){
        //获取token
        String token = getWxAccessToken();
        System.out.println(token);
        try {
            System.out.println("111");
            byte [] byteArr=multipartFile.getBytes();
            InputStream inputStream = new ByteArrayInputStream(byteArr);
            String contentType=multipartFile.getContentType();
            System.out.println("2222");
            //返回监测结果
            return checkImg(inputStream,contentType, token);
        }catch (Exception e) {
            return true;
        }
    }

    private  Boolean checkImg(InputStream inputStream, String type , String token) {
        return WxUtil.checkImg(inputStream, type,token);
    }

    /**
     * 获取小程序全局唯一后台接口调用凭据（access_token）
     * @return
     */
    public  String getWxAccessToken(){
        //从redis缓存中获取AccessToken，有且为过期，直接返回；否则重新获取
        String accessToken =redisService.getCache("accessToken");
        if(!isEmpty(accessToken)){
            return accessToken;
        }
        //重新获取accessToken，并存入redis
        String newToken = getAccessToken();
        //存入redis
        redisService.setExpireCache("accessToken", newToken, 7000L);
        return newToken;
    }
    /**
     * 调用微信开放接口 获取小程序全局唯一后台接口调用凭据（access_token）
     * @return
     */
    public  String getAccessToken(){

        String APPID= smsConfig.getWechatAppId();
        String APPSECRET=smsConfig.getWechatSecret();

        String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+APPSECRET;
        HttpRequestUtil hru = new HttpRequestUtil();
        JSONObject json = hru.sendUrlGet(accessTokenUrl);
        String access_token = json.getString("access_token");
        if(isEmpty(access_token)){
            access_token="";
        }

        System.out.println("json:"+json.toString());

        return access_token;
    }


}
