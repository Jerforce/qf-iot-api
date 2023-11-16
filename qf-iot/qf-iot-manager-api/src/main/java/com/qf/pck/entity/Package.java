package com.qf.pck.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 租户套餐表
 * </p>
 *
 * 
 *  2023-11-19
 */
@Getter
@Setter
public class Package implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 套餐编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 套餐名
     */
    private String name;

    /**
     * 状态（0正常 1停用）
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 套餐的权限
     */
    private String permIds;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新者
     */
    private String updater;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer deleted;

    /**
     * 套餐的价格
     */
    private Object price;

    /**
     * 套餐时间单位
     */
    private String timeunit;

    /**
     * 套餐时间
     */
    private Integer timenum;


    @TableField(exist = false)
    private List<PackageTenantPerms> perms;
}
