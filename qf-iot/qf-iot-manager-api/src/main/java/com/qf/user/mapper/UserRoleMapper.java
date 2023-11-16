package com.qf.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.user.entity.User;
import com.qf.user.entity.UserRole;

/**
 * <p>
 * 用户角色 Mapper 接口
 * </p>
 *
 * 
 *  2023-11-28
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {
    User login(String username);
}
