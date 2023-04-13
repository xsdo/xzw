package cn.zealon.readingcloud.account.feign.client;

import cn.zealon.readingcloud.account.feign.fallback.BindingClientFallBack;
import cn.zealon.readingcloud.account.feign.fallback.TeacherClientFallBack;
import cn.zealon.readingcloud.common.pojo.xzwusers.UBinding;
import cn.zealon.readingcloud.common.pojo.xzwusers.UTeacher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(contextId = "teacher", name = "light-reading-cloud-account", fallbackFactory = TeacherClientFallBack.class)
public interface TeacherClient {

    @GetMapping("/account/uTeacher/getAllTeacher")
    List<UTeacher> getAllTeacher();
}
