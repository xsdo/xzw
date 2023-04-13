package cn.zealon.readingcloud.account.feign.fallback;

import cn.zealon.readingcloud.account.feign.client.BindingClient;
import cn.zealon.readingcloud.account.feign.client.TeacherClient;
import cn.zealon.readingcloud.common.pojo.xzwusers.UBinding;
import cn.zealon.readingcloud.common.pojo.xzwusers.UTeacher;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class TeacherClientFallBack implements FallbackFactory<TeacherClient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherClientFallBack.class);

    @Override
    public TeacherClient create(Throwable cause) {
        return new TeacherClient() {
            @Override
            public List<UTeacher> getAllTeacher(){
                LOGGER.error("查询班级失败：{}", "", cause.getMessage());
                return null;
            }
        };
    }
}
