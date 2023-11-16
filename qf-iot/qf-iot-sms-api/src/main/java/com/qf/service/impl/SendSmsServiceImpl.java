package com.qf.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.qf.mapper.SendSmsMapper;
import com.qf.pojo.TenantsPhone;
import com.qf.service.SendSmsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Jerforce
 * @date 2023/11/16 星期四 11:53:42
 */
public class SendSmsServiceImpl implements SendSmsService {
    @Autowired
    private SendSmsService sendSmsService;
    @Autowired
    private SendSmsMapper sendSmsMapper;
    @Override
    public String SendSms(String Phone, String SignName, String TemplateCode) {
        //连接aliyun
        //调用发送短信的方法

        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", "LTAI5tAW5zwPU3iNebfjbcpf", "8SZZFq736PvFGdQJ9EUIJegUO8ATsb");
        IAcsClient client = new DefaultAcsClient(profile);

        //构建请求
        CommonRequest request = new CommonRequest();
        request.setSysMethod(com.aliyuncs.http.MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-shanghai");
        request.putQueryParameter("Phone", Phone);
        request.putQueryParameter("SignName", SignName);
        request.putQueryParameter("TemplateCode", TemplateCode);
        //构建一个短信验证码
        HashMap<String, Object> map = new HashMap<String, Object>();
        int PhoneCode = (int)((Math.random()*9+1)*100000);
        map.put("code",PhoneCode);
        //将验证码放入数据库中

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        sendSmsMapper.insertPhoneCode(PhoneCode,format);

        request.putQueryParameter("TemplateParam", com.alibaba.fastjson.JSONObject.toJSONString(map));
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return "短信发送成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "短信发送失败！";
        }
    }
    /*根据id查询所使用的短信模板*/

    @Override
    public TenantsPhone selectPhone(String phone) {
        TenantsPhone tenantsPhone = sendSmsMapper.selectPhone(phone);
        return tenantsPhone;
    }
}
