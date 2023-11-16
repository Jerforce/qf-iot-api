package com.qf.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @author Jerforce
 * @date 2023/11/16 星期四 19:15:04
 */
public class SMSUtils {

    /**
     * 发送短信
     * @param signName 签名
     * @param templateCode 模板
     * @param phone 手机号
     * @param param 参数
     */
    public static void sendMessage(String signName, String templateCode,String phone,String param){
        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", "LTAI5tAW5zwPU3iNebfjbcpf", "8SZZFq736PvFGdQJ9EUIJegUO8ATsb");
        IAcsClient client = new DefaultAcsClient(profile);

        SendSmsRequest request = new SendSmsRequest();
        request.setSysRegionId("cn-shanghai");
        request.setPhoneNumbers(phone);
        request.setSignName(signName);
        request.setTemplateCode(templateCode);
        request.setTemplateParam("{\"code\":\""+param+"\"}");
        try {
            SendSmsResponse response = client.getAcsResponse(request);
            System.out.println("短信发送成功");
        }catch (ClientException e) {
            e.printStackTrace();
        }
    }
}