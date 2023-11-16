package com.qf.device.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 租户物模型属性表
 * </p>
 *
 * Jerforce
 *  2023-11-19
 */
@Getter
@Setter
@TableName("qf_device_model")
public class DeviceModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 租户物模型主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 设备key
     */
    private String deviceKey;

    /**
     * 租户id
     */
    private String tenantId;

    /**
     * 租户物模型属性名称
     */
    private String propertyName;

    /**
     * 物模型类型：1.属性；2.事件；3.服务
     */
    private Byte modelType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer deleted;

    /**
     * 物模型描述
     */
    private String description;

    /**
     * 物模型模板ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long templateId;
}
