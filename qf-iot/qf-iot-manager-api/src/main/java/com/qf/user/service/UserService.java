package com.qf.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.core.vo.R;
import com.qf.user.entity.User;

/**
 * <p>
 * 管理员用户表 服务类
 * </p>
 *
 * 
 *  2023-11-18
 */
public interface UserService extends IService<User> {
    R login(User user);
}
