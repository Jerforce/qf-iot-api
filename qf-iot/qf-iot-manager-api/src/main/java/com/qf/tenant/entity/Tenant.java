package com.qf.tenant.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 租户表
 * </p>
 *
 * 
 *  2023-11-19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("qf_tenant")
public class Tenant implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private String id;

    /**
     * 租户用户名
     */
    private String name;

    /**
     * 租户密码
     */
    private String pwd;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司工商执照编码
     */
    private String companyCode;

    /**
     * 是否可用, 1可用, 2不可用
     */
    private Integer enable;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 逻辑删标识  0未删除  1删除
     */
    private Byte deleted;

    /**
     * 租户key
     */
    private String accessKey;

    /**
     * 租户秘钥
     */
    private String accessSecurit;

    /**
     * 审核状态,0待审核, 1通过, 2不通过
     */
    private Integer auditStatus;

    /**
     * 租户手机号
     */
    private String phone;
}
