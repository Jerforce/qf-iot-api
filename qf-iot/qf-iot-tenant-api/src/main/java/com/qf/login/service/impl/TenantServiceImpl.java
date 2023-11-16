package com.qf.login.service.impl;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.core.vo.Result;
import com.qf.login.entity.Tenant;
import com.qf.login.mapper.TenantMapper;
import com.qf.login.service.TenantService;
import com.qf.login.utils.JwtUtil;
import com.qf.pck.entity.Package;
import com.qf.pck.entity.PackageTenantPerms;
import com.qf.pck.mapper.PackageMapper;
import com.qf.pck.mapper.PackageTenantPermsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 租户表 服务实现类
 * </p>
 *
 * @author
 *  2023-11-26
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements TenantService {

    @Autowired
    private TenantMapper tenantMapper;
    @Autowired
    private PackageMapper packageMapper;
    @Autowired
    private PackageTenantPermsMapper packageTenantPermsMapper;

    @Override
    public Result login(Tenant tenant) {
        if (tenant == null) {
            return new Result(1, "请输入账号密码", "");
        }
        QueryWrapper<Tenant> tenantQueryWrapper = new QueryWrapper<>();
        tenantQueryWrapper.lambda().eq(Tenant::getName, tenant.getName());

        //网页传回的账号用户名查询到数据库中的user1
        Tenant tenant1 = this.tenantMapper.selectOne(tenantQueryWrapper);
        if (tenant1 == null) {
            return new Result(1, "用户名或密码不正确", "");
        }

        //网页拿到的密码md5加密后和数据库中的密码比较
        String s = SecureUtil.md5(tenant.getPwd());

        if (!s.equals(tenant1.getPwd())) {
            return new Result(1, "用户名或密码不正确", "");
        }

        //密码也成功比较后，开始办法jwt令牌传给前端
        tenant1.setPwd("");
        Package pck = packageMapper.selectPerms(tenant1.getId());
        List<String> permList = null;
        if (pck == null) {
           permList=new ArrayList<>();
        }
        else{
            List<String> list = JSONUtil.toList(pck.getPermIds(), String.class);
            permList = new ArrayList<>();
            for (String s1 : list) {
                PackageTenantPerms perms = packageTenantPermsMapper.selectById(s1);
                permList.add(perms.getPermName());
            }}

            tenant1.setPerms(permList);
            String token = JwtUtil.generatorHoursToken(tenant1, 1);
            return new Result(0, "登录成功", token);
        }

    @Override
    public Result register(Tenant tenant) {
        QueryWrapper<Tenant> qw = new QueryWrapper<>();
        qw.lambda().eq(Tenant::getName, tenant.getName());
        Tenant tenant1 = tenantMapper.selectOne(qw);
        if (tenant1 == null) {
            String idStr = IdWorker.getIdStr();
            String s = SecureUtil.md5(tenant.getPwd());
            long rand = (long) (Math.random() * 900000 + 100000);
            String acc = String.valueOf(rand);
            tenant.setId(idStr);
            tenant.setPwd(s);
            tenant.setAuditStatus(1);
            tenant.setCreateTime(new Date());
            tenant.setUpdateTime(new Date());
            tenant.setAccessSecurit(SecureUtil.md5(acc));
            SnowflakeGenerator snowflakeGenerator = new SnowflakeGenerator();
            Long l = snowflakeGenerator.next();
            tenant.setAccessKey(String.valueOf(l));
            tenant.setId(String.valueOf(l));
            int i = tenantMapper.insert(tenant);

            if (i > 0) {
                return new Result(0, "注册成功");

            } else {
                return new Result(1, "注册失败");

            }
        }
        return new Result(1, "用户已存在");
    }
}