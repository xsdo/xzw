package cn.zealon.readingcloud.account.feign.client;

import cn.zealon.readingcloud.account.feign.fallback.FlowersClientFallBack;
import cn.zealon.readingcloud.account.feign.fallback.TableClientFallBack;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 喜欢看客户端feign接口
 * @author: zealon
 * @since: 2019/7/4
 */
@FeignClient(contextId = "table", name = "light-reading-cloud-account", fallbackFactory = TableClientFallBack.class)
public interface TableClient {

    @GetMapping("/account/uTable/toTableAdd")
    void toTableAdd(@RequestParam("userId") Long userId, @RequestParam("tableId") Long tableId);

}
