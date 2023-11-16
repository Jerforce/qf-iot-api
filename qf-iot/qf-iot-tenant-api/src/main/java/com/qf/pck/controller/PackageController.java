package com.qf.pck.controller;

import com.qf.core.vo.R;
import com.qf.pck.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class PackageController {
    @Autowired
    private PackageService packageService;

    @RequestMapping("/all")
    public R all() {
        return packageService.all();
    }


}
