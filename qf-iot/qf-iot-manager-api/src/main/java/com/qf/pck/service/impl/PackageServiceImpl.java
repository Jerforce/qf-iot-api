package com.qf.pck.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.core.vo.R;
import com.qf.pck.entity.Package;
import com.qf.pck.entity.PackageTenantPerms;
import com.qf.pck.mapper.PackageMapper;
import com.qf.pck.mapper.PackageTenantPermsMapper;
import com.qf.pck.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 租户套餐表 服务实现类
 * </p>
 *
 * @author
 *  2023-11-29
 */
@Service
public class PackageServiceImpl extends ServiceImpl<PackageMapper, Package> implements PackageService {

    @Autowired
    private PackageMapper packageMapper;
    @Autowired
    private PackageTenantPermsMapper packageTenantPermsMapper;

    @Override
    public R all() {
        QueryWrapper<Package> queryWrapper = new QueryWrapper<>();
        List<Package> packages = packageMapper.selectList(queryWrapper);
        for (Package pck : packages) {
            List<String> ids = JSONUtil.toList(pck.getPermIds(), String.class);
            List<PackageTenantPerms> perms = new ArrayList<>();
            for (String id : ids) {
                perms.add(packageTenantPermsMapper.selectById(id));
            }
            pck.setPerms(perms);
        }
        return new R(true, 0, "success", packages);
    }
}
