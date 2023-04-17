package cn.zealon.readingcloud.account.wxpay;


import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/account/api/v1/pay/test")
public class PayTestController {
    @Autowired
    private WxPayConfig wxPayConfig;

    @Autowired
    private CloseableHttpClient wxPayClient;

    @Autowired
    private Verifier verifier;

    @ApiOperation("1.支付测试接口")
    @GetMapping("/pay/test")
    public Map<String, String> getCheckNum() {
        WeChatBasePayData payData = new WeChatBasePayData();
        payData.setTitle("支付测试商品");
        payData.setOrderId(IdWorker.getIdStr()); //测试时随机生成一个，代表订单号
        payData.setPrice(new BigDecimal("0.01"));
        payData.setNotify(WxNotifyType.REFUND_NOTIFY);

        String path = WxPayCommon.wxNativePay(wxPayConfig, payData, wxPayClient);
        Map<String, String> map = new HashMap();
        map.put("path", path);
        return map;
    }



    @ApiOperation("微信支付回调接口")
    @PostMapping("/wx/callback")
    public String courseNative(HttpServletRequest request, HttpServletResponse response) {
        return WxPayCallbackUtil.wxPaySuccessCallback(request, response, verifier, wxPayConfig, callbackData -> {
            // TODO 处理你的业务逻辑，下面说一下一般业务逻辑处理方法
            log.info("微信支付返回的信息：{}", callbackData);
            // 1.根据订单id获取订单信息

            // 2.判断金额是否相符，如果不相符则调用退款接口，并取消该订单，通知客户支付金额不符

            // 3.查询订单状态是否是未支付，如果是未支付则改为已支付，填充其他逻辑，

            // 4.如果是其他状态综合你的业务逻辑来处理

            // 5.如果是虚拟物品，则对应充值，等等其他逻辑
        });
    }

    @ApiOperation("根据微信订单号查询订单")
    @PostMapping("/search/order/transaction/{transactionId}")
    public WxchatCallbackSuccessData searchByTransactionId(@PathVariable String transactionId) {
        return  WxPaySearchOrderUtil.searchByTransactionId(wxPayConfig,transactionId,wxPayClient);
    }

    @ApiOperation("根据商户订单号查询")
    @PostMapping("/search/order/{orderId}")
    public WxchatCallbackSuccessData searchByOrderId(@PathVariable String orderId) {
        return  WxPaySearchOrderUtil.searchByOrderId(wxPayConfig,orderId,wxPayClient);
    }

    @ApiOperation("退款申请测试")
    @GetMapping("/refund/{orderId}")
    public String refund(@PathVariable String orderId) {
        WeChatRefundParam param = new WeChatRefundParam();
        param.setOrderId(orderId);
        String refundOrderId = IdWorker.getIdStr();
        log.info("refundOrderId:{}", refundOrderId);
        param.setRefundOrderId(refundOrderId);
        param.setReason("商品售罄");
        param.setNotify(WxNotifyType.REFUND_NOTIFY);
        param.setRefundMoney(new BigDecimal("0.01"));
        param.setTotalMoney(new BigDecimal("0.01"));
        return WxPayRefundUtil.refundPay(wxPayConfig, param, wxPayClient);
    }

    @ApiOperation("微信退款回调接口")
    @PostMapping("/wx/refund/callback")
    public String refundWechatCallback(HttpServletRequest request, HttpServletResponse response) {
        return WxPayCallbackUtil.wxPayRefundCallback(request, response, verifier, wxPayConfig, new WechatRefundCallback() {
            @Override
            public void success(WxchatCallbackRefundData refundData) {
                // TODO 退款成功的业务逻辑，例如更改订单状态为退款成功等
            }

            @Override
            public void fail(WxchatCallbackRefundData refundData) {
                // TODO 特殊情况下退款失败业务处理，例如银行卡冻结需要人工退款，此时可以邮件或短信提醒管理员，并携带退款单号等关键信息
            }
        });
    }

}
