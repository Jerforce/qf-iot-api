package com.qf.tenant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.tenant.entity.Tenant;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 租户表 Mapper 接口
 * </p>
 *
 * 
 *  2023-11-19
 */
@Mapper
public interface TenantMapper extends BaseMapper<Tenant> {

}
