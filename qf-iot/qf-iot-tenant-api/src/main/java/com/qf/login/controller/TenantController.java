package com.qf.login.controller;

import com.qf.core.vo.R;
import com.qf.core.vo.Result;
import com.qf.login.entity.Tenant;
import com.qf.login.service.TenantService;
import com.qf.login.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 租户表 前端控制器
 * </p>
 *
 * 
 *  2023-11-26
 */
@RestController
@RequestMapping("/tenant")
@CrossOrigin
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @PostMapping("/login")
    public Result login(@RequestBody Tenant tenant){
        return tenantService.login(tenant);
    }

    @PostMapping("/register")
    public Result register(@RequestBody Tenant tenant){
        return tenantService.register(tenant);
    }
    @RequestMapping("/verify")
    public R verifyToken(@RequestHeader("token") String token){

        try {
            JwtUtil.verifyToken(token);

            return new R(true,0,"valid token",null);
        } catch (Exception exception) {
            exception.printStackTrace();

            return new R(false,1,"invalid token",null);
        }


    }


}
