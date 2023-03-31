package cn.zealon.readingcloud.book.chatgpt;

import cn.hutool.core.collection.CollectionUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class ChatGptApiTest {
    //通过spring 的resttemplate调用chatgpt api
    private static final RestTemplate restTemplate = new RestTemplate();
    //openai的api key，可以写死或从配置文件读取，这里读取的环境变量
//    private static final String apiKey = System.getenv("OPENAI_API_KEY");
    private static final String apiKey = "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";

    public static void main(String[] args) {
        final String question = "google和openai比,哪个公司更有潜力";
        final StringBuilder sb = new StringBuilder();
        while (true){
            //循环调用，将返回的内容附加到问题里，解决返回的答案被截断的问题
            AIResponse response = getAiResponse(question+"\n A:"+sb);
            if(response!= null ){
// 如果返回不为null，提取返回内容
                sb.append(CollectionUtil.getFirst(response.getChoices()).getText());
            }
            if(response == null || CollectionUtil.getFirst(response.getChoices()).finish()){
                //返回null，或者回答结束，退出循环
                break;
            }

        }

        log.info("result:{}",sb);


    }


    private static AIResponse getAiResponse(final String question){
        final Map<String,Object> data = Maps.newHashMap();
        data.put("model","gpt-3.5-turbo");
//        data.put("model","text-davinci-003");
        data.put("prompt",question);
        data.put("temperature",0);
        data.put("max_tokens",50);

        log.info("apikey:{}",apiKey);
        if(StringUtils.isBlank(apiKey)){
            log.error("apikey is empty");
        }else {
            final URI uri = URI.create("https://api.openai.com/v1/completions");
            MultiValueMap<String,String> headers = new HttpHeaders();
            headers.add("Content-Type","application/json");
            headers.add("Authorization","Bearer "+apiKey);

            final RequestEntity<String> request = new RequestEntity<>(JsonUtils.toJson(data),headers,HttpMethod.POST, uri);
            final ResponseEntity<AIResponse> responseEntity = restTemplate.exchange(request, AIResponse.class);
            AIResponse responseEntityBody = responseEntity.getBody();
            log.info("{}",responseEntity);
            if(!Objects.isNull(responseEntityBody)){
                final AIResponseChoice first = CollectionUtil.getFirst(responseEntityBody.getChoices());
                if(!Objects.isNull(first)){
                    return responseEntityBody;
                }
            }else {
                log.info("{}",responseEntity);
            }
        }
        return null;
    }

    @Data
    public static class AIResponse{
        private List<AIResponseChoice> choices;
    }

    @Data
    public static class AIResponseChoice{
        private String text;
        @JsonProperty("finish_reason")
        private String finishReason;

        public boolean finish(){
            return !Objects.equals("length",finishReason);
        }

    }


}

