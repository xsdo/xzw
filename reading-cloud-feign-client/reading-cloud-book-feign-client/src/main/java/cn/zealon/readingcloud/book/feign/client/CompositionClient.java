package cn.zealon.readingcloud.book.feign.client;

import cn.zealon.readingcloud.book.feign.fallback.BookClientFallBack;
import cn.zealon.readingcloud.book.feign.fallback.CompositionClientFallBack;
import cn.zealon.readingcloud.common.pojo.book.Book;
import cn.zealon.readingcloud.common.pojo.xzwresources.Composition;
import cn.zealon.readingcloud.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(contextId = "composition", name = "light-reading-cloud-book", fallbackFactory = CompositionClientFallBack.class)
public interface CompositionClient {

    @RequestMapping("/book/composition/queryRandoms")
    List<Composition> queryRandoms(@RequestParam("size") int size);

}
