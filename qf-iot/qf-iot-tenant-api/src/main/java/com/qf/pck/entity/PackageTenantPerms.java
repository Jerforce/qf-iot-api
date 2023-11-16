package com.qf.pck.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
@TableName("package_tenant_perms")
public class PackageTenantPerms implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限名字
     */
    private String permName;

    /**
     * 权限访问uri地址
     */
    private String permUrl;

    /**
     * 权限描述
     */
    private String permDesc;
}
