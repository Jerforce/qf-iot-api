package com.qf.pck.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * 
 *  2023-11-19
 */
@Getter
@Setter
@TableName("package_order")
public class PackageOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id")
    private String id;

    /**
     * 租户id
     */
    private String tenantId;

    /**
     * 套餐id
     */
    private String packageId;

    /**
     * 支付时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    /**
     * 0：表示未支付1：表示已经支付
     */
    private Integer payStatus;

    /**
     * 支付金额
     */
    private String money;

    /**
     * 支付订单号
     */
    private String payno;

    /**
     * 套餐开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 套餐结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 下单时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
