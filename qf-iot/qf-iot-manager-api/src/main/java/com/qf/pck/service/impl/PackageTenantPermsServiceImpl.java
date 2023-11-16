package com.qf.pck.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.core.vo.R;
import com.qf.pck.entity.PackageTenantPerms;
import com.qf.pck.mapper.PackageTenantPermsMapper;
import com.qf.pck.service.PackageTenantPermsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * 
 *  2023-11-19
 */
@Service
public class PackageTenantPermsServiceImpl extends ServiceImpl<PackageTenantPermsMapper, PackageTenantPerms> implements PackageTenantPermsService {

    @Autowired
    private PackageTenantPermsMapper packageTenantPermsMapper;
    @Override
    public R all() {
        QueryWrapper<PackageTenantPerms> queryWrapper = new QueryWrapper<>();
        List<PackageTenantPerms> permList = packageTenantPermsMapper.selectList(queryWrapper);
        return new R(true, 0, "success", permList);
    }
}
