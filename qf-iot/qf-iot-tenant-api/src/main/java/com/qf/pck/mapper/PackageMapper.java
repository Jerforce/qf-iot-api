package com.qf.pck.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.pck.entity.Package;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 租户套餐表 Mapper 接口
 * </p>
 *
 * 
 *  2023-11-29
 */
@Mapper
public interface PackageMapper extends BaseMapper<Package> {
    Package selectPerms(String id);

}
