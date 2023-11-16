package com.qf.pay.service.impl;

import com.github.wxpay.sdk.WXPayRequest;
import com.github.wxpay.sdk.WXPayUtil;
import com.qf.core.vo.R;
import com.qf.pay.config.WXConfig;
import com.qf.pay.controller.WebSocketEndpoint;
import com.qf.pay.service.IPayService;
import com.qf.pck.entity.Package;
import com.qf.pck.entity.PackageOrder;
import com.qf.pck.mapper.PackageMapper;
import com.qf.pck.mapper.PackageOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PayService implements IPayService {

    @Autowired
    private PackageOrderMapper packageOrderMapper;
    @Autowired
    private PackageMapper packageMapper;

    @Override
    public R pay(String orderId) {
        //调用微信第三方接口完成统一下单
        PackageOrder packageOrder = packageOrderMapper.selectById(orderId);
        Double payAmountFen = packageOrder.getMoney() * 100;
        String notifyUrl = "http://8.140.201.171//pay/notify";
        String codeUrl = payOrder(orderId, payAmountFen.intValue(), notifyUrl);
        return new R(true, 0, "success", codeUrl);
    }

    @Override
    public String isPay(String resultXML) throws Exception {
        boolean signatureValid = WXPayUtil.isSignatureValid(resultXML, new WXConfig().getKey());
        if (signatureValid) {//合法通知
            Map<String, String> wxNotifyData = WXPayUtil.xmlToMap(resultXML);
            String out_trade_no = wxNotifyData.get("out_trade_no");
            String total_fee = wxNotifyData.get("total_fee");

            //3:比较订单金额是否与商户侧的订单金额一致
            PackageOrder order = packageOrderMapper.selectById(out_trade_no);
            //if(total_fee.equals(order.getMoney()*100+"") ){//金额一致
            if (true) {
                //4:修改商户侧的订单状态为已支付
                order.setPayStatus(1);
                order.setPayno(out_trade_no);
                order.setPayTime(new Date());

                //修改套餐的有效时间
                order.setStartTime(new Date());
                //获取套餐
                Package pck = packageMapper.selectById(order.getPackageId());
                if ("月".equals(pck.getTimeunit())) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.MONTH, pck.getTimenum());
                    order.setEndTime(calendar.getTime());
                }

                if ("年".equals(pck.getTimeunit())) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.YEAR, pck.getTimenum());
                    order.setEndTime(calendar.getTime());
                }

                if ("季度".equals(pck.getTimeunit())) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.MONTH, pck.getTimenum() * 3);
                    order.setEndTime(calendar.getTime());
                }

                packageOrderMapper.updateById(order);

                //响应微信
                Map resMap = new HashMap();
                resMap.put("return_code", "SUCCESS");
                resMap.put("return_msg", "fail");

                //log.info("支付成功,异步通知,业务处理成功");

                //通过websocket 告诉客户端，支付成功
                WebSocketEndpoint.sessions.get(out_trade_no)
                        .getBasicRemote().sendText("paysuccess");


                try {
                    return WXPayUtil.mapToXml(resMap);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return "";
                }


            } else {//金额不一致
                //响应微信
                Map resMap = new HashMap();
                resMap.put("return_code", "FAIL");
                resMap.put("return_msg", "金额不一致");

                try {
                    return WXPayUtil.mapToXml(resMap);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return "";
                }
            }


        } else {//非法通知

            //响应微信
            Map resMap = new HashMap();
            resMap.put("return_code", "FAIL");
            resMap.put("return_msg", "非法通知");

            try {
                return WXPayUtil.mapToXml(resMap);
            } catch (Exception exception) {
                exception.printStackTrace();
                return "";
            }

        }
    }


    private String payOrder(String ordersn, Integer fee, String notifyUrl) {
        try {
            WXConfig wxConfig = new WXConfig();
            //请求参数
            Map requestParamMap = new HashMap();
            requestParamMap.put("appid", wxConfig.getAppID());
            requestParamMap.put("mch_id", wxConfig.getMchID());
            requestParamMap.put("nonce_str", WXPayUtil.generateNonceStr());
            requestParamMap.put("body", "商品下单");
            requestParamMap.put("out_trade_no", ordersn);
            requestParamMap.put("total_fee","1");
            requestParamMap.put("spbill_create_ip", "127.0.0.1");
            requestParamMap.put("notify_url", notifyUrl);
            requestParamMap.put("trade_type", "NATIVE ");

            //将请求参数转换成xml格式
            String requestParamXML = WXPayUtil.generateSignedXml(requestParamMap, wxConfig.getKey());

            //发送请求(httpclient)
            WXPayRequest wxPayRequest = new WXPayRequest(wxConfig);
            String responseXML = wxPayRequest.requestWithoutCert("/pay/unifiedorder", UUID.randomUUID().toString(), requestParamXML, false);
            System.out.println(responseXML);

            //解析响应结果
            Map<String, String> responseMap = WXPayUtil.xmlToMap(responseXML);

            String code_url = responseMap.get("code_url");


            System.out.println(code_url);

            return code_url;
        } catch (Exception exception) {
            exception.printStackTrace();

            return "";
        }
    }


}
