package com.qf.pck.controller;

import com.qf.core.vo.R;
import com.qf.pck.entity.Package;
import com.qf.pck.service.PackageService;
import com.qf.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 租户套餐表 前端控制器
 * </p>
 *
 * @author
 *  2023-11-29
 */
@RestController
@RequestMapping("/package")
@CrossOrigin
@PreAuthorize("hasAuthority('sys:pck:select')")
public class PackageController {
    @Autowired
    private PackageService packageService;

    @RequestMapping("/save")
    public R save(@RequestBody Package pck) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        pck.setStatus(0);
        pck.setCreator(user.getUserName());
        pck.setCreateTime(new Date());
        pck.setDeleted(0);
        pck.setTimenum(1);
        packageService.save(pck);
        return new R(true,0,"success",null);
    }

    @RequestMapping("/all")
    public R all() {
        return packageService.all();
    }
    @RequestMapping("/delete/{pckId}")
    public R delete(@PathVariable String pckId) {
        packageService.removeById(pckId);
        return new R(true,0,"success",null);
    }
    @RequestMapping("/gotoUpd/{pckId}")
    public R gotoUpd(@PathVariable String pckId) {
        Package pck = packageService.getById(pckId);
        return new R(true,0,"success",pck);
    }
    @RequestMapping("/update")
    public R update(@RequestBody Package pck) {
        packageService.updateById(pck);
        return new R(true,0,"success",null);
    }
}
