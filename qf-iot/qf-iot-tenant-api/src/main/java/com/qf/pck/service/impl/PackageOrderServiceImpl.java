package com.qf.pck.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.pck.entity.PackageOrder;
import com.qf.pck.mapper.PackageOrderMapper;
import com.qf.pck.service.PackageOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * 
 *  2023-11-29
 */
@Service
public class PackageOrderServiceImpl extends ServiceImpl<PackageOrderMapper, PackageOrder> implements PackageOrderService {

    @Autowired
    private PackageOrderMapper packageOrderMapper;
    @Override
    public PackageOrder selectByPckTenant(String pckId, String tenantId) {
        QueryWrapper<PackageOrder> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(PackageOrder::getPackageId,pckId).eq(PackageOrder::getTenantId,tenantId).eq(PackageOrder::getPayStatus,0);
        PackageOrder packageOrder = packageOrderMapper.selectOne(queryWrapper);
        return packageOrder;
    }
}
