package com.qf.user.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.core.vo.R;
import com.qf.user.entity.User;
import com.qf.user.mapper.UserMapper;
import com.qf.user.service.UserService;
import com.qf.user.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 管理员用户表 服务实现类
 * </p>
 *
 * @author
 *  2023-11-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public R login(User user) {
        if (StringUtils.isEmpty(user)) {
            return new R(false, 1, "用户参数不合法", null);
        }
        User dbuser = userMapper.login(user.getUserName());
        if(StringUtils.isEmpty(dbuser)){
            return new R(false,1,"用户名或密码错误",null);
        }
        if(!dbuser.getPassword().equals(SecureUtil.md5(user.getPassword()))){
            return new R(false,1,"用户名或密码错误",null);
        }else {
            String token = JwtUtil.generatorHoursToken(dbuser, 1);
            return new R(false,0,"success",token);
        }
    }
}
