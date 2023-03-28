package cn.zealon.readingcloud.account.feign.fallback;

import cn.zealon.readingcloud.account.feign.client.FlowersClient;
import cn.zealon.readingcloud.account.feign.client.TableClient;
import com.alibaba.fastjson.JSONObject;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 喜欢看客户端feign接口快速失败
 * @author: zealon
 * @since: 2019/7/4
 */
@Component
public class TableClientFallBack implements FallbackFactory<TableClient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TableClientFallBack.class);

    @Override
    public TableClient create(Throwable cause) {
        return new TableClient() {
            @Override
            public void toTableAdd(@RequestParam("userId") Long userId, @RequestParam("tableId") Long tableId){
                LOGGER.error("添加[{}]标签失败：{}", userId, cause.getMessage());
            }
        };
    }
}
