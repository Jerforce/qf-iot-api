package com.qf.pay.controller;

import com.github.wxpay.sdk.WXPayUtil;
import com.qf.core.vo.R;
import com.qf.pay.service.impl.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pay")
@CrossOrigin
public class PayController {
    @Autowired
    private PayService payService;
    @RequestMapping("/payOrder/{orderId}")
    public R payOrder(@PathVariable String orderId){
        return payService.pay(orderId);
    }
    @RequestMapping("/notify")
    public String notify(HttpServletRequest request){
        try {
            //log.info("支付成功,异步通知......");

            //1：接受微信的通知参数
            ServletInputStream inputStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while((len = inputStream.read(buffer)) != -1){
                outSteam.write(buffer,0,len);
            }
            outSteam.close();
            inputStream.close();
            String resultXML = new String(outSteam.toByteArray(),"utf-8");
            //log.info("支付成功,异步通知,通知参数{}",resultXML);
            //2：验签
            return payService.isPay(resultXML);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //响应微信
        Map resMap = new HashMap();
        resMap.put("return_code","FAIL");
        resMap.put("return_msg","非法通知");

        try {
            return WXPayUtil.mapToXml(resMap);
        } catch (Exception exception) {
            exception.printStackTrace();
            return  "";
        }

    }

}
