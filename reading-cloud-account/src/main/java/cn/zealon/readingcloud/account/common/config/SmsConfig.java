package cn.zealon.readingcloud.account.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * 短信和wechat配置
 * @author: zealon
 * @since: 2020/4/17
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "send")
public  class SmsConfig {

    /** 微信key */
    private String wechatAppId;
    /** 微信密钥*/
    private String wechatSecret;
    /** 短信key */
    private String smsKey;
    /** 短信密钥 */
    private String smsSecret;

}
