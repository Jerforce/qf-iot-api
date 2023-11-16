package com.qf.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * Jerforce
 * @date 2023/11/16 星期四 11:41:29
 */
@TableName("tenants_phone")
public class TenantsPhone {
    private  int id;
    private static String phone;
    private static String SignName;
    private static String TemplateCode;

    public TenantsPhone() {
    }

    public TenantsPhone(int id, String phone, String SignName, String TemplateCode) {
        this.id = id;
        this.phone = phone;
        this.SignName = SignName;
        this.TemplateCode = TemplateCode;
    }

    public String toString() {
        return "TenantsPhone{id = " + id + ", phone = " + phone + ", SignName = " + SignName + ", TemplateCode = " + TemplateCode + "}";
    }

    /**
     * 获取
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取
     * @return phone
     */
    public static String getPhone() {
        return phone;
    }

    /**
     * 设置
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取
     * @return SignName
     */
    public static String getSignName() {
        return SignName;
    }

    /**
     * 设置
     * @param SignName
     */
    public void setSignName(String SignName) {
        this.SignName = SignName;
    }

    /**
     * 获取
     * @return TemplateCode
     */
    public static String getTemplateCode() {
        return TemplateCode;
    }

    /**
     * 设置
     * @param TemplateCode
     */
    public void setTemplateCode(String TemplateCode) {
        this.TemplateCode = TemplateCode;
    }
}
