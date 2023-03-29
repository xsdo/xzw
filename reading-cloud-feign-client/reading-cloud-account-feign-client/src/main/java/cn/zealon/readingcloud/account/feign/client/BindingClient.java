package cn.zealon.readingcloud.account.feign.client;

import cn.zealon.readingcloud.account.feign.fallback.BindingClientFallBack;
import cn.zealon.readingcloud.account.feign.fallback.FlowersClientFallBack;
import cn.zealon.readingcloud.common.pojo.xzwusers.UBinding;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 喜欢看客户端feign接口
 * @author: zealon
 * @since: 2019/7/4
 */
@FeignClient(contextId = "binding", name = "light-reading-cloud-account", fallbackFactory = BindingClientFallBack.class)
public interface BindingClient {

    @GetMapping("/account/uBinding/queryByTeacherId")
    List<UBinding> queryByTeacherId(@RequestParam("teacherId") Long teacherId);

}
