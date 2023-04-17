package cn.zealon.readingcloud.account.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * oss配置信息
 */
@Component
@ConfigurationProperties(prefix ="aliyun.oss")
public class OssProperties {
    /**
     * 填写Bucket所在地域对应的Endpoint
     */
    private String endpoint;
    /**
     * AccessKey的id
     */
    private String keyId;
    /**
     * AccessKey的密钥
     */
    private String keySecret;
    /**
     * 阿里云储存文件的Bucket名称
     */
    private String bucketName;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getKeySecret() {
        return keySecret;
    }

    public void setKeySecret(String keySecret) {
        this.keySecret = keySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}

