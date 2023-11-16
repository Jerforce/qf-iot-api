package com.qf.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.user.entity.User;

/**
 * <p>
 * 管理员用户表 Mapper 接口
 * </p>
 *
 *
 *  2023-11-18
 */
public interface UserMapper extends BaseMapper<User> {
    User login(String username);
}
