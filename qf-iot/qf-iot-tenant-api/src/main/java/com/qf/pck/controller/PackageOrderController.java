package com.qf.pck.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.qf.core.vo.R;
import com.qf.login.entity.Tenant;
import com.qf.pck.entity.Package;
import com.qf.pck.entity.PackageOrder;
import com.qf.pck.service.PackageOrderService;
import com.qf.pck.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * 
 *  2023-11-29
 */
@RestController
@RequestMapping("/package-order")
@CrossOrigin
public class PackageOrderController {
    @Autowired
    private PackageOrderService packageOrderService;
    @Autowired
    private PackageService packageService;
    @RequestMapping("/save/{pckId}")
    public R save(@PathVariable String pckId){

        Tenant tenant = (Tenant) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PackageOrder order=packageOrderService.selectByPckTenant(pckId,tenant.getId());
        if (order != null) {
            return new R(true,0,"订单已存在，请先支付",order.getId());
        }
        String id = IdWorker.getIdStr();
        Package pck = packageService.getById(pckId);
        PackageOrder packageOrder=new PackageOrder();
        packageOrder.setId(id);
        packageOrder.setTenantId(tenant.getId());
        packageOrder.setPackageId(pckId);
        packageOrder.setPayStatus(0);
        packageOrder.setMoney(pck.getPrice());
        packageOrderService.save(packageOrder);
        return new R(true,0,"订单创建成功",id);
    }
    @RequestMapping("/findByOrder")
    private R findByOrder(){
        Tenant tenant = (Tenant) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        QueryWrapper<PackageOrder> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(PackageOrder::getPayStatus,1).eq(PackageOrder::getTenantId,tenant.getId());
        PackageOrder order = packageOrderService.getOne(queryWrapper);
        if (order != null) {
            return new R(true,1,"该用户用拥有套餐",order);
        }
        return new R(true,0,"该用户还未购买套餐",null);
    }
}
