package com.qf.service.impl;

import com.qf.service.SendSms;
import com.qf.utils.SMSUtils;

/**
 * Jerforce
 * @date 2023/11/16 星期四 10:55:07
 */
public class SendSmsImpl implements SendSms {
    @Override
    public void sendMessage(String signName, String templateCode, String phone, String param) {

        /**
         * 发送短信
         * @param signName 签名
         * @param templateCode 模板
         * @param phone 手机号
         * @param param 参数
         */
        SMSUtils.sendMessage("测试专用模板", "SMS_1XXXX909", "1768XXXX992", "1234");
    }
}
