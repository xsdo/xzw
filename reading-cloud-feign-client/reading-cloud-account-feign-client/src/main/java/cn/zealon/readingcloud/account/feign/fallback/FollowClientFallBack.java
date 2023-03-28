package cn.zealon.readingcloud.account.feign.fallback;

import cn.zealon.readingcloud.account.feign.client.FlowersClient;
import cn.zealon.readingcloud.account.feign.client.FollowClient;
import cn.zealon.readingcloud.common.pojo.xzwusers.UFollow;
import com.alibaba.fastjson.JSONObject;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 喜欢看客户端feign接口快速失败
 * @author: zealon
 * @since: 2019/7/4
 */
@Component
public class FollowClientFallBack implements FallbackFactory<FollowClient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FollowClientFallBack.class);

    @Override
    public FollowClient create(Throwable cause) {
        return new FollowClient() {
            @Override
            public List<UFollow> queryFollows(Long userId){
                LOGGER.error("查询用户[{}]关注失败：{}", userId, cause.getMessage());
                return null;
            }
        };
    }
}
