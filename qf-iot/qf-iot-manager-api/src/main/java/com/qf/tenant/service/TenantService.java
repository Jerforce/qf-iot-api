package com.qf.tenant.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.core.vo.PageDTO;
import com.qf.core.vo.PageResult;
import com.qf.tenant.entity.Tenant;

/**
 * <p>
 * 租户表 服务类
 * </p>
 *
 * 
 *  2023-11-19
 */
public interface TenantService extends IService<Tenant> {

    PageResult selectByStatus(PageDTO<Tenant> pageDTO);
    PageResult all(PageDTO<Tenant> pageDTO);
}