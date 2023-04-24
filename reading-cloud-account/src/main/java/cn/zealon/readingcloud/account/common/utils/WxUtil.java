package cn.zealon.readingcloud.account.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * 微信小程序 工具类
 * @author Administrator
 *
 */
public class WxUtil {



    /**
     * 调用微信开放接口msgSecCheck检测文字内容
     * 频率限制:单个appid调用上限为2000次/分钟，1,000,000次/天
     * @param msg 要检测的文字内容
     * @param accessToken 小程序全局唯一后台接口调用凭据
     * @return true:含敏感信息   false：正常
     */
    public static Boolean checkMsg(String msg, String accessToken){
        String url = "https://api.weixin.qq.com/wxa/msg_sec_check?access_token=" + accessToken;
        //创建客户端
        HttpClient httpclient = HttpClients.createDefault();
        //创建一个post请求
        HttpPost request = new HttpPost(url);
        //设置响应头
        request.setHeader("Content-Type", "application/json;charset=UTF-8");
        //通过fastJson设置json数据
        JSONObject postData = new JSONObject();
        //设置要检测的内容
        postData.put("content", msg);
        String jsonString = postData.toString();
        request.setEntity(new StringEntity(jsonString,"utf-8"));
        //由客户端执行(发送)请求
        try {
            HttpResponse response = httpclient.execute(request);
            // 从响应模型中获取响应实体
            HttpEntity entity = response.getEntity();
            //得到响应结果
            String result = EntityUtils.toString(entity,"utf-8");
            //打印检测结果
            System.out.println("检测结果:"+result);
            //将响应结果变成json
            JSONObject resultJsonObject = JSONObject.parseObject(result);

            String errcode =resultJsonObject.getString("errcode");
            if(errcode.equals("87014")){//当content内含有敏感信息，则返回87014
                return true;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 检测图片是否含有违法违规内容
     * 频率限制:单个appid调用上限为1000次/分钟，100,000次/天
     * @param inputStream 图片文件  流multipartFile.getInputStream()
     * @param contentType 图片文件类型  multipartFile.getContentType()
     * @return true:含敏感信息   false：正常
     * media 要检测的图片文件，格式支持PNGJPEGJPGGIF, 像素不超过750x1334
     */
    public static Boolean checkImg(InputStream inputStream, String contentType ,String accessToken){
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            CloseableHttpResponse response = null;
            HttpPost request = new HttpPost("https://api.weixin.qq.com/wxa/img_sec_check?access_token=" + accessToken);
            request.addHeader("Content-Type", "application/octet-stream");
            byte[] byt = new byte[inputStream.available()];
            inputStream.read(byt);
            request.setEntity(new ByteArrayEntity(byt, ContentType.create(contentType)));
            response = httpclient.execute(request);
            HttpEntity httpEntity = response.getEntity();
            String result = EntityUtils.toString(httpEntity, "UTF-8");// 转成string
            JSONObject jso = JSONObject.parseObject(result);

            String errcode = jso.getString("errcode");
            if(errcode.equals("87014")){//当content内含有敏感信息，则返回87014
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args) {
        String access_token="36_jocRV09Bx3lck-xxyYWvQWdv-5otrobLiwil3i-cvELcwnfnl-dUT2O2fiMyQbAYhYLBBE5kcPkRERfb079aeuT7POHmWyJPcCVoCJmTvq1J5__cBZl9Ayahmvxv_EDIP18lgYfiNZi7TZKmVTKfAIAECT";
        String content="特3456书yuuo莞6543李zxcz蒜7782法fgnv级 完2347全dfji试3726测asad感3847知qwez到";

        checkMsg(content, access_token);
    }
}

