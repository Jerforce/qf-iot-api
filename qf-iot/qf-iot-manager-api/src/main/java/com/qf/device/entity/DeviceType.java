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
 * 设备类型表
 * </p>
 *
 * Jerforce
 *  2023-11-26
 */
@Getter
@Setter
@TableName("qf_device_type")
public class DeviceType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 所属行业ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long industryId;

    /**
     * 设备类型名称, 例如:光照传感器
     */
    private String name;

    /**
     * 类型：1.网关；2.传感器；3.继电器；4.摄像头;
     */
    private Integer deviceType;

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
    private Boolean deleted;
    @TableField(exist = false)
    private String industryName;
}
