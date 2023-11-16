package com.qf.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.area.mapper.AreaMapper;
import com.qf.core.vo.PageDTO;
import com.qf.core.vo.PageResult;
import com.qf.product.entity.Product;
import com.qf.product.mapper.ProductMapper;
import com.qf.product.service.ProductService;
import com.qf.tenant.mapper.TenantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 产品表 服务实现类
 * </p>
 *
 *
 *  2023-11-30
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private TenantMapper tenantMapper;
    @Autowired
    private AreaMapper areaMapper;
    @Override
    public PageResult page(PageDTO<Product> pageDto) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(pageDto.getWhere().getName())) {
            queryWrapper.lambda().like(Product::getName, pageDto.getWhere().getName());
        }
        if (!StringUtils.isEmpty(pageDto.getWhere().getUsername())) {
            queryWrapper.lambda().like(Product::getUsername, pageDto.getWhere().getUsername());
        }
        if (!StringUtils.isEmpty(pageDto.getWhere().getProductKey())) {
            queryWrapper.lambda().like(Product::getProductKey, pageDto.getWhere().getProductKey());
        }
        if (!StringUtils.isEmpty(pageDto.getWhere().getAreaCode())) {
            queryWrapper.lambda().eq(Product::getAreaCode, pageDto.getWhere().getAreaCode());
        }
        IPage<Product> iPage = new Page<>();
        iPage.setSize(pageDto.getSize());
        iPage.setCurrent(pageDto.getNow());
        IPage<Product> page = productMapper.selectPage(iPage, queryWrapper);
        PageResult<Product> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        for (Product product : page.getRecords()) {
            product.setTenantName(tenantMapper.selectById(product.getTenantId()).getName());

            product.setAreaName(areaMapper.selectById(product.getAreaCode()).getName());
        }
        pageResult.setList(page.getRecords());
        return pageResult;
    }
}
