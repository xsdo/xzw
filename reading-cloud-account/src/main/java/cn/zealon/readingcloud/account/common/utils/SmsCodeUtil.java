package cn.zealon.readingcloud.account.common.utils;


import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;

import java.util.Random;

public class SmsCodeUtil {

    //生成验证码
    public static String codeFen(){
        String str = "0123456789";
        //将字符串转换为一个新的字符数组。
        char[] VerificationCodeArray = str.toCharArray();
        Random random = new Random();
        int count = 0;//计数器
        StringBuilder stringBuilder = new StringBuilder();
        while(true) {
            //随机生成一个随机数
            int index = random.nextInt(VerificationCodeArray.length);
            char c = VerificationCodeArray[index];
            //限制四位不重复数字
            if (stringBuilder.indexOf(c + "") == -1) {
                stringBuilder.append(c);
                //计数器加1
                count++;
            }
            //当count等于4时结束，随机生成四位数的验证码
            if (count == 4) {
                break;
            }
        }
        return stringBuilder.toString();
    }

    public static void sendSmsCode (String phoneNumber,String code)throws Exception{
        Client client=SmsCodeUtil.createClient("","");
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phoneNumber)
                .setSignName("新作文杂志社")
                .setTemplateCode("SMS_248140966")
                .setTemplateParam("{\"code\":\""+code+"\"}");
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        com.aliyun.dysmsapi20170525.models.SendSmsResponse resp = client.sendSmsWithOptions(sendSmsRequest, runtime);
        com.aliyun.teaconsole.Client.log(com.aliyun.teautil.Common.toJSONString(resp));

    }



    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    /**
     * 使用STS鉴权方式初始化账号Client，推荐此方式。本示例默认使用AK&SK方式。
     * @param accessKeyId
     * @param accessKeySecret
     * @param securityToken
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dysmsapi20170525.Client createClientWithSTS(String accessKeyId, String accessKeySecret, String securityToken) throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret)
                // 必填，您的 Security Token
                .setSecurityToken(securityToken)
                // 必填，表明使用 STS 方式
                .setType("sts");
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }




    public static void main(String[] args_) throws Exception {
        java.util.List<String> args = java.util.Arrays.asList(args_);
        // 工程代码泄露可能会导致AccessKey泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考，建议使用更安全的 STS 方式，更多鉴权访问方式请参见：https://help.aliyun.com/document_detail/378657.html
        com.aliyun.dysmsapi20170525.Client client = SampleTest.createClient("ACCESS_KEY_ID", "ACCESS_KEY_SECRET");
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setPhoneNumbers("18954189554")
                .setSignName("新作文杂志社")
                .setTemplateCode("SMS_248140966")
                .setTemplateParam("{\"code\":\"1111\"}");
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        com.aliyun.dysmsapi20170525.models.SendSmsResponse resp = client.sendSmsWithOptions(sendSmsRequest, runtime);
        com.aliyun.teaconsole.Client.log(com.aliyun.teautil.Common.toJSONString(resp));
    }
}
