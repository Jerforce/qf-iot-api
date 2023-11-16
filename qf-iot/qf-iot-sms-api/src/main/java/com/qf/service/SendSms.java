package com.qf.service;

/**
 * Jerforce
 * @date 2023/11/16 星期四 10:53:31
 */
public interface SendSms {
    void sendMessage(String signName, String templateCode, String phone, String param);
}
