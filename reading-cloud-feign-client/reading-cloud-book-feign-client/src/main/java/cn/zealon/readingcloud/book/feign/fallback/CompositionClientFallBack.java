package cn.zealon.readingcloud.book.feign.fallback;

import cn.zealon.readingcloud.book.feign.client.BookClient;
import cn.zealon.readingcloud.book.feign.client.CompositionClient;
import cn.zealon.readingcloud.common.pojo.book.Book;
import cn.zealon.readingcloud.common.pojo.xzwresources.Composition;
import cn.zealon.readingcloud.common.result.Result;
import cn.zealon.readingcloud.common.result.ResultUtil;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CompositionClientFallBack implements FallbackFactory<CompositionClient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompositionClientFallBack.class);

    @Override
    public CompositionClient create(Throwable cause) {
        return new CompositionClient() {
            @Override
            public List<Composition> queryRandoms(int size){
                LOGGER.error("获取随机文章[{}]失败：{}", size, cause.getMessage());
                return null;
            }
        };
    }
}
