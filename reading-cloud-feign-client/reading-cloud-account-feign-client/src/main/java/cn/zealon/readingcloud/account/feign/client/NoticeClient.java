package cn.zealon.readingcloud.account.feign.client;

import cn.zealon.readingcloud.account.feign.fallback.FlowersClientFallBack;
import cn.zealon.readingcloud.account.feign.fallback.NoticeClientFallBack;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 喜欢看客户端feign接口
 * @author: zealon
 * @since: 2019/7/4
 */
@FeignClient(contextId = "notice", name = "light-reading-cloud-account", fallbackFactory = NoticeClientFallBack.class)
public interface NoticeClient {

    @GetMapping("/account/uNotice/doNotice")
    void doNotice(@RequestParam("userId") Long userId,@RequestParam("name") String name,@RequestParam("type") int type,@RequestParam("coment") String coment);

}
