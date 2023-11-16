package com.qf.controller;

import com.qf.pojo.TenantsPhone;
import com.qf.service.SendSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Jerforce
 * @date 2023/11/16 星期四 11:52:48
 */
public class SendSmsController {
    @Autowired
    private SendSmsService sendSmsService;

    @RequestMapping("/Sms")
    @ResponseBody
    public String SendSms(@RequestParam("phone") String phone) {
        /*从数据库中查询号码，AccesssKey账户，AccessKey密码*/
        /*此处可以取出多种模板*/
        TenantsPhone tenantsPhone = sendSmsService.selectPhone(phone);
        if (phone == null) {
            return "该手机号未注册！";
        }
        String message = sendSmsService.SendSms(TenantsPhone.getPhone(), TenantsPhone.getSignName(), TenantsPhone.getTemplateCode());
        return message;

    }
}
