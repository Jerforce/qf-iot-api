package com.qf.tenant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.core.vo.PageDTO;
import com.qf.core.vo.PageResult;
import com.qf.tenant.entity.Tenant;
import com.qf.tenant.mapper.TenantMapper;
import com.qf.tenant.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 租户表 服务实现类
 * </p>
 *
 * 
 *  2023-11-19
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements TenantService {

    @Autowired
    private TenantMapper tenantMapper;
    @Override
    public PageResult selectByStatus(PageDTO<Tenant> pageDto) {
        QueryWrapper<Tenant> queryWrapper=new QueryWrapper<>();
        if (!StringUtils.isEmpty(pageDto.getWhere().getName())) {
            queryWrapper.lambda().like(Tenant::getName, pageDto.getWhere().getName());
        }
        if (!StringUtils.isEmpty(pageDto.getWhere().getAccessKey())) {
            queryWrapper.lambda().like(Tenant::getAccessKey, pageDto.getWhere().getAccessKey());
        }
        queryWrapper.lambda().eq(Tenant::getAuditStatus,0);
        IPage<Tenant> iPage = new Page<>();
        iPage.setSize(pageDto.getSize());
        iPage.setCurrent(pageDto.getNow());
        IPage<Tenant> page = tenantMapper.selectPage(iPage, queryWrapper);
        PageResult<Tenant> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        pageResult.setList(page.getRecords());
        return pageResult;
    }

    @Override
    public PageResult all(PageDTO<Tenant> pageDto) {
        QueryWrapper<Tenant> queryWrapper=new QueryWrapper<>();
        if (!StringUtils.isEmpty(pageDto.getWhere().getName())) {
            queryWrapper.lambda().like(Tenant::getName, pageDto.getWhere().getName());
        }
        if (!StringUtils.isEmpty(pageDto.getWhere().getAccessKey())) {
            queryWrapper.lambda().like(Tenant::getAccessKey, pageDto.getWhere().getAccessKey());
        }
        queryWrapper.lambda().ne(Tenant::getAuditStatus,0);
        IPage<Tenant> iPage = new Page<>();
        iPage.setSize(pageDto.getSize());
        iPage.setCurrent(pageDto.getNow());
        IPage<Tenant> page = tenantMapper.selectPage(iPage, queryWrapper);
        PageResult<Tenant> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        pageResult.setList(page.getRecords());
        return pageResult;
    }
}