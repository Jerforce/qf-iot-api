package com.qf.pck.controller;

import com.qf.core.vo.R;
import com.qf.pck.service.PackageOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("hasAuthority('sys:order:select')")
public class PackageOrderController {

    @Autowired
    private PackageOrderService packageOrderService;
    @RequestMapping("/all")
    public R all() {
        return packageOrderService.all();
    }
    @RequestMapping("/delete/{orderId}")
    public R delete(@PathVariable String orderId) {
        packageOrderService.removeById(orderId);
        return new R(true,0,"success",null);
    }






}
