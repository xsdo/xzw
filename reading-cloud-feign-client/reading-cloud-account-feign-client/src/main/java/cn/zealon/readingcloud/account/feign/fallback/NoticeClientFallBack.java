package cn.zealon.readingcloud.account.feign.fallback;

import cn.zealon.readingcloud.account.feign.client.FlowersClient;
import cn.zealon.readingcloud.account.feign.client.NoticeClient;
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
public class NoticeClientFallBack implements FallbackFactory<NoticeClient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeClientFallBack.class);

    @Override
    public NoticeClient create(Throwable cause) {
        return new NoticeClient() {
            @Override
            public void doNotice(Long userId,String name,int type,String coment){
                LOGGER.error("添加[{}]通知失败：{}", userId, cause.getMessage());
            }
        };
    }
}
