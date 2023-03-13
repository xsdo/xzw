package cn.zealon.readingcloud.account.feign.client;

import cn.zealon.readingcloud.account.feign.fallback.LikeSeeClientFallBack;
import cn.zealon.readingcloud.account.feign.fallback.UserAttributeClientFallBack;
import cn.zealon.readingcloud.common.pojo.xzwusers.UAttribute;
import cn.zealon.readingcloud.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户属性客户端feign接口
 * @author: zealon
 * @since: 2019/7/4
 */
@FeignClient(contextId = "attribute", name = "light-reading-cloud-account", fallbackFactory = UserAttributeClientFallBack.class)
public interface UserAttributeClient {
    @RequestMapping(value = "account/uAttribute/queryByUserId",method= RequestMethod.GET)
    UAttribute queryByUserId(@RequestParam("userId") Long userId);
}
