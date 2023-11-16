package com.qf.pck.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.core.vo.R;
import com.qf.pck.entity.Package;

/**
 * <p>
 * 租户套餐表 服务类
 * </p>
 *
 *
 *  2023-11-19
 */
public interface PackageService extends IService<Package> {

    R all();
}
