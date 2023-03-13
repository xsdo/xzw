package cn.zealon.readingcloud.account.feign.fallback;

import cn.zealon.readingcloud.account.feign.client.LikeSeeClient;
import cn.zealon.readingcloud.account.feign.client.UserAttributeClient;
import cn.zealon.readingcloud.common.pojo.xzwusers.UAttribute;
import cn.zealon.readingcloud.common.result.Result;
import cn.zealon.readingcloud.common.result.ResultUtil;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * 用户属性客户端feign接口快速失败
 * @author: zealon
 * @since: 2019/7/4
 */
@Component
public class UserAttributeClientFallBack implements FallbackFactory<UserAttributeClient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAttributeClientFallBack.class);

    @Override
    public UserAttributeClient create(Throwable cause) {
        return new UserAttributeClient() {
            @Override
            public UAttribute queryByUserId(Long userId) {
                LOGGER.error("获取用户[{}]属性失败：{}", userId, cause.getMessage());
                return null;
            }
        };
    }
}
