package cn.zealon.readingcloud.account.feign.fallback;

import cn.zealon.readingcloud.account.feign.client.BindingClient;
import cn.zealon.readingcloud.account.feign.client.FlowersClient;
import cn.zealon.readingcloud.common.pojo.xzwusers.UBinding;
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
public class BindingClientFallBack implements FallbackFactory<BindingClient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BindingClientFallBack.class);

    @Override
    public BindingClient create(Throwable cause) {
        return new BindingClient() {
            @Override
            public List<UBinding> queryByTeacherId(Long teacherId){
                LOGGER.error("查询班级[{}]失败：{}", teacherId, cause.getMessage());
                return null;
            }
        };
    }
}
