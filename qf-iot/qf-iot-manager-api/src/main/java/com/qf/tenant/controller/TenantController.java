package com.qf.tenant.controller;


import com.qf.core.vo.PageDTO;
import com.qf.core.vo.PageResult;
import com.qf.core.vo.R;
import com.qf.tenant.entity.Tenant;
import com.qf.tenant.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户表 前端控制器
 * </p>
 *
 * 
 *  2023-11-19
 */
@RestController
@RequestMapping("/tenant")
@CrossOrigin
@PreAuthorize("hasAuthority('sys:tenant:select')")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @RequestMapping("/pageverify")
    public R pageverify(@RequestBody PageDTO<Tenant> pageDTO){
        PageResult pageResult = tenantService.selectByStatus(pageDTO);
        return new R(true,0,"success",pageResult);
    }
    @RequestMapping("/page")
    public R page(@RequestBody PageDTO<Tenant> pageDTO){
        PageResult pageResult = tenantService.all(pageDTO);
        return new R(true,0,"success",pageResult);
    }
    @RequestMapping("/updateverify")
    public R updateverify(@RequestBody Tenant tenant){
        tenantService.updateById(tenant);
        return new R(true,0,"success",null);
    }
    @RequestMapping("/update")
    public R update(@RequestBody Tenant tenant){
        tenant.setEnable(tenant.getEnable()==1?2:1);
        tenantService.updateById(tenant);
        return new R(true,0,"success",null);
    }
}
