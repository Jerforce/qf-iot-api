package com.qf.model.entity;

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
 * 物模型模板表
 * </p>
 *
 *
 *  2023-11-01
 */
@Getter
@Setter
@TableName("qf_model_template")
public class ModelTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 设备类型ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long deviceTypeId;

    /**
     * 模型名称
     */
    private String propertyName;

    /**
     * 物模型类型：1.属性；2.事件；3.服务
     */
    private Integer modelType;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    @TableField(exist = false)
    private String deviceTypeName;
}
