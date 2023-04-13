package cn.zealon.readingcloud.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 图书资源中心
 * @author zealon
 */
@EnableFeignClients(basePackages = {"cn.zealon.readingcloud.account.feign"})
@SpringBootApplication(scanBasePackages={"cn.zealon.readingcloud.book", "cn.zealon.readingcloud.common", "cn.zealon.readingcloud.account.feign"})
@EnableScheduling
public class BookApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

}
