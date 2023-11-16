package com.qf.pck.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.core.vo.R;
import com.qf.pck.entity.PackageOrder;

/**
 * <p>
 *  服务类
 * </p>
 *
 * 
 *  2023-11-19
 */
public interface PackageOrderService extends IService<PackageOrder> {

    R all();
}
