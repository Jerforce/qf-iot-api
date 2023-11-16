package com.qf.pck.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 *  2023-11-29
 */
@Getter
@Setter
@TableName("package_order")
public class PackageOrder implements Serializable {

    private static final long serialVersionUID = 1L;
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
    private Date payTime;

    /**
     * 0：表示未支付1：表示已经支付
     */
    private Integer payStatus;

    /**
     * 支付金额
     */
    private Double money;

    /**
     * 支付订单号
     */
    private String payno;

    /**
     * 套餐开始时间
     */
    private Date startTime;

    /**
     * 套餐结束时间
     */
    private Date endTime;

    /**
     * 下单时间
     */
    private Date createTime;
}
