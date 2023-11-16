package com.qf.pck.controller;

import com.qf.core.vo.R;
import com.qf.pck.entity.PackageTenantPerms;
import com.qf.pck.service.PackageTenantPermsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * 
 *  2023-11-19
 */
@RestController
@RequestMapping("/package-tenant-perms")
@CrossOrigin
/*@PreAuthorize("hasAuthority('sys:perm:select')")*/
public class PackageTenantPermsController {

    @Autowired
    private PackageTenantPermsService packageTenantPermsService;
    @RequestMapping("/gotoAdd")
    public R gotoAdd() {
        List<PackageTenantPerms> list = packageTenantPermsService.list();
        return new R(true,0,"success",list);
    }

    @RequestMapping("/all")
    public R all() {
        return packageTenantPermsService.all();
    }
    @RequestMapping("/save")
    public R save(@RequestBody PackageTenantPerms packageTenantPerms) {
        packageTenantPermsService.save(packageTenantPerms);
        return new R(true,0,"success",null);
    }

    @RequestMapping("/delete/{permId}")
    public R delete(@PathVariable String permId) {
        packageTenantPermsService.removeById(permId);
        return new R(true,0,"success",null);
    }

    @RequestMapping("/gotoUpd/{permId}")
    public R gotoUpd(@PathVariable String permId) {
        PackageTenantPerms perms = packageTenantPermsService.getById(permId);
        return new R(true,0,"success",perms);
    }
    @RequestMapping("/update")
    public R update(@RequestBody PackageTenantPerms packageTenantPerms) {
        packageTenantPermsService.updateById(packageTenantPerms);
        return new R(true,0,"success",null);
    }



}
