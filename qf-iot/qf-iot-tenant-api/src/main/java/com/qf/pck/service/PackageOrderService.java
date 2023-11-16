package com.qf.pck.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.pck.entity.PackageOrder;

/**
 * <p>
 *  服务类
 * </p>
 *
 * 
 *  2023-11-29
 */
public interface PackageOrderService extends IService<PackageOrder> {

    PackageOrder selectByPckTenant(String pckId,String tenantId);
}
