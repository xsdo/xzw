package cn.zealon.readingcloud.account.feign.fallback;

import cn.zealon.readingcloud.account.feign.client.FlowersClient;
import cn.zealon.readingcloud.account.feign.client.LikeSeeClient;
import cn.zealon.readingcloud.common.result.Result;
import cn.zealon.readingcloud.common.result.ResultUtil;
import com.alibaba.fastjson.JSONObject;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 喜欢看客户端feign接口快速失败
 * @author: zealon
 * @since: 2019/7/4
 */
@Component
public class FlowersClientFallBack implements FallbackFactory<FlowersClient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlowersClientFallBack.class);

    @Override
    public FlowersClient create(Throwable cause) {
        return new FlowersClient() {
            @Override
            public JSONObject addFlowers(Long userId, int flowers, String remarks){
                LOGGER.error("添加[{}]积分失败：{}", userId, cause.getMessage());
                return null;
            }
        };
    }
}
