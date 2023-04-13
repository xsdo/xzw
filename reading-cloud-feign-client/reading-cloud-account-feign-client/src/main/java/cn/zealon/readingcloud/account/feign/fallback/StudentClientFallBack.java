package cn.zealon.readingcloud.account.feign.fallback;

import cn.zealon.readingcloud.account.feign.client.StudentClient;
import cn.zealon.readingcloud.account.feign.client.TeacherClient;
import cn.zealon.readingcloud.common.pojo.xzwusers.Student;
import cn.zealon.readingcloud.common.pojo.xzwusers.UTeacher;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class StudentClientFallBack implements FallbackFactory<StudentClient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentClientFallBack.class);

    @Override
    public StudentClient create(Throwable cause) {
        return new StudentClient() {
            @Override
            public List<Student> getAllStudent(){
                LOGGER.error("查询学生失败：{}", "", cause.getMessage());
                return null;
            }
        };
    }
}
