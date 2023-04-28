package cn.zealon.readingcloud.account.controller;


import cn.zealon.readingcloud.account.service.PayInfoService;
import cn.zealon.readingcloud.account.service.UVipService;
import cn.zealon.readingcloud.account.service.UserOrderService;
import cn.zealon.readingcloud.account.wxpay.*;
import cn.zealon.readingcloud.common.pojo.xzwusers.PayInfo;
import cn.zealon.readingcloud.common.pojo.xzwusers.UserOrder;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Api(description = "微信支付接口")
@RestController
@RequestMapping("/account/wepay")
public class WePayController {
    @Autowired
    private WxPayConfig wxPayConfig;

    @Autowired
    private CloseableHttpClient wxPayClient;

    @Autowired
    private Verifier verifier;

    @Resource
    private UserOrderService userOrderService;

    @Resource
    private PayInfoService payInfoService;

    @Resource
    private UVipService uVipService;


    @ApiOperation("支付接口")
    @PostMapping("/wx/pay/{orderNo}")
    public Map<String, String> wePayByOrderNo(@PathVariable String orderNo) {
        UserOrder userOrder =this.userOrderService.queryByOrderNode(orderNo);
        if (userOrder == null) {
            Map<String, String> map = new HashMap();
            map.put("code","-1");
            map.put("data", "订单还未创建");
            return map;
        }
        Map<String, String> map = new HashMap();
        if (userOrder.getStatus()==4||userOrder.getStatus() == 2){
            map.put("code","-1");
            map.put("data","该订单已完成，请不要重复支付");
            return map;
        }else if (userOrder.getStatus() == 5){
            map.put("code","-1");
            map.put("data","该订单已关闭，请重新下单");
            return map;
        }
        WeChatBasePayData payData = new WeChatBasePayData();
        payData.setTitle(userOrder.getName());
        payData.setOrderId(orderNo);
        payData.setPrice(new BigDecimal(userOrder.getPayment()));
        payData.setNotify(WxNotifyType.REFUND_NOTIFY);
        String path = WxPayCommon.wxNativePay(wxPayConfig, payData, wxPayClient);
        map.put("code","00");
        map.put("path", path);
        return map;
    }

    @ApiOperation("支付查询接口")
    @PostMapping("/wx/search/{orderNo}")
    public Map<String, String> weSearchByOrderNo(@PathVariable String orderNo) {
        UserOrder userOrder =this.userOrderService.queryByOrderNode(orderNo);
        if (userOrder == null) {
            Map<String, String> map = new HashMap();
            map.put("code","-1");
            map.put("data", "订单还未创建");
            return map;
        }
        Map<String, String> map = new HashMap();
        WxchatCallbackSuccessData wxchatCal=WxPaySearchOrderUtil.searchByOrderId(wxPayConfig,orderNo,wxPayClient);
        if (wxchatCal != null){
            if (wxchatCal.getTradestate().equals("SUCCESS")) {
                //更新订单状态
                userOrder.setStatus(2);
                userOrder.setPaymentTime(new Date());
                this.userOrderService.update(userOrder);

                PayInfo payInfo =new PayInfo();
                payInfo.setIsused(0);
                payInfo.setOrderNo(orderNo);
                List<PayInfo>payInfoList=this.payInfoService.queryAll(payInfo);
                if (payInfoList.isEmpty()){
                    payInfo.setCreateTime(new Date());
                    payInfo.setUpdateTime(new Date());
                    payInfo.setUserId(userOrder.getUserId());
                    payInfo.setPayPlatform(1);
                    payInfo.setPlatformNumber(wxchatCal.getTransactionId());
                    payInfo.setPlatformStatus("SUCCESS");
                    this.payInfoService.insert(payInfo);
                }
                if (userOrder.getStatus()==2){
                    if (userOrder.getName().endsWith("月卡")){
                        //更新订单状态
                        userOrder.setStatus(4);
                        userOrder.setSendTime(new Date());
                        userOrder.setEndTime(new Date());
                        this.userOrderService.update(userOrder);
                        this.uVipService.toVip(userOrder.getUserId(), 1,1);
                    }else if (userOrder.getName().endsWith("年卡")){
                        //更新订单状态
                        userOrder.setStatus(4);
                        userOrder.setSendTime(new Date());
                        userOrder.setEndTime(new Date());
                        this.userOrderService.update(userOrder);
                        this.uVipService.toVip(userOrder.getUserId(), 1,12);
                    }else if (userOrder.getName().endsWith("测试支付商品")){
                        //更新订单状态
                        userOrder.setStatus(4);
                        userOrder.setSendTime(new Date());
                        userOrder.setEndTime(new Date());
                        this.userOrderService.update(userOrder);
                        this.uVipService.toVip(userOrder.getUserId(), 1,12);
                    }
                }

                map.put("code","00");
                map.put("data", "支付成功");
            }else {
                //关闭订单
                userOrder.setStatus(5);
                userOrder.setCloseTime(new Date());
                this.userOrderService.update(userOrder);
                map.put("code","-1");
                map.put("data", "您还未支付成功或支付出现问题");
            }
        }else {
            //关闭订单
            userOrder.setStatus(5);
            userOrder.setCloseTime(new Date());
            this.userOrderService.update(userOrder);
            map.put("code","-1");
            map.put("data", "您还未支付成功或支付出现问题");
        }
        return map;
    }



    @ApiOperation("微信支付回调接口")
//    @PostMapping("/wx/callback")
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
//    @PostMapping("/search/order/transaction/{transactionId}")
    public WxchatCallbackSuccessData searchByTransactionId(@PathVariable String transactionId) {
        return  WxPaySearchOrderUtil.searchByTransactionId(wxPayConfig,transactionId,wxPayClient);
    }

    @ApiOperation("根据商户订单号查询")
//    @PostMapping("/search/order/{orderId}")
    public WxchatCallbackSuccessData searchByOrderId(@PathVariable String orderId) {
        return  WxPaySearchOrderUtil.searchByOrderId(wxPayConfig,orderId,wxPayClient);
    }

    @ApiOperation("退款申请测试")
//    @GetMapping("/refund/{orderId}")
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
//    @PostMapping("/wx/refund/callback")
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
