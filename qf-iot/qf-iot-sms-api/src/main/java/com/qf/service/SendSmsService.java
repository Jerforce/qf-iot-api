package com.qf.service;

import com.qf.pojo.TenantsPhone;

/**
 * Jerforce
 * @date 2023/11/16 星期四 11:53:15
 */
public interface SendSmsService {

    String SendSms(String Phone,String SignName,String TemplateCode);

    TenantsPhone selectPhone(String Phone);
}
