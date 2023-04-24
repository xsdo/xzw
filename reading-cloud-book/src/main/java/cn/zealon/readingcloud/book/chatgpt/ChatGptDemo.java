package cn.zealon.readingcloud.book.chatgpt;
import cn.hutool.http.*;
import cn.hutool.json.JSONObject;
import java.util.HashMap;
import java.util.Map;



public class ChatGptDemo {
    public static void main(String[] args) {
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("Content-Type","application/json;charset=UTF-8");

        JSONObject json = new JSONObject();
        //选择模型
        json.set("model","text-davinci-003");
        //添加我们需要输入的内容
        json.set("messages","Oracle 计算年龄，精确到天，格式为xx岁xx月xx天？");
        json.set("prompt","Oracle 计算年龄，精确到天，格式为xx岁xx月xx天？");
        json.set("temperature",0.9);
        json.set("max_tokens",2048);
        json.set("top_p",1);
        json.set("frequency_penalty",0.0);
        json.set("presence_penalty",0.6);

        HttpResponse response = HttpRequest.post("https://open.aiproxy.xyz/v1/chat/completions")
//        HttpResponse response = HttpRequest.post("https://api.openai.com/v1/completions")
                .headerMap(headers, false)
                .bearerAuth("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")
                .body(String.valueOf(json))
                .timeout(5 * 60 * 1000)
                .execute();

        System.out.println(response.body());
    }}
