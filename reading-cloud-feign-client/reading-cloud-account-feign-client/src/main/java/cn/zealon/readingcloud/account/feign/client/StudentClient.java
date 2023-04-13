package cn.zealon.readingcloud.account.feign.client;

import cn.zealon.readingcloud.account.feign.fallback.StudentClientFallBack;
import cn.zealon.readingcloud.account.feign.fallback.TeacherClientFallBack;
import cn.zealon.readingcloud.common.pojo.xzwusers.Student;
import cn.zealon.readingcloud.common.pojo.xzwusers.UTeacher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(contextId = "student", name = "light-reading-cloud-account", fallbackFactory = StudentClientFallBack.class)
public interface StudentClient {

    @GetMapping("/account/student/getAllStudent")
    List<Student> getAllStudent();
}
