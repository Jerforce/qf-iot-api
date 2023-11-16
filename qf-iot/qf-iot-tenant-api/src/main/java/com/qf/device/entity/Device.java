package com.qf.device.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 设备表
 * </p>
 *
 * wang
 *  2023-11-26
 */
@Getter
@Setter
@TableName("qf_device")
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 设备标识
     */
    private String deviceKey;

    /**
     * 设备类型
     */
    private Long deviceTypeId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除 ： 0 未删除   1删除
     */
    private Integer deleted;

    /**
     * 所属产品
     */
    private Long productId;

    /**
     * 父设备
     */
    private Long parentId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 分组ID
     */
    private Long groupId;

    /**
     * 设备激活状态:1未激活, 2激活
     */
    private Integer active;

    /**
     * 设备认证秘钥
     */
    private String deviceSecret;

    /**
     * 设备在关联产品中的唯一编号，同一个产品下的多个设备编号不能重复
     */
    private Integer deviceIndex;

    @TableField(exist = false)
    private String productName;

    @TableField(exist = false)
    private String typeName;
}
