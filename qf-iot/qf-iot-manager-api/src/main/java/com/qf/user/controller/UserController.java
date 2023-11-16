package com.qf.user.controller;

import com.qf.core.vo.R;
import com.qf.user.entity.User;
import com.qf.user.service.UserService;
import com.qf.user.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 管理员用户表 前端控制器
 * </p>
 *
 * 
 *  2023-11-18
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public R login(@RequestBody User user){
        return userService.login(user);
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
