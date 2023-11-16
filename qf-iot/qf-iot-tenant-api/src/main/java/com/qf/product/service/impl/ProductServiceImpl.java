package com.qf.product.service.impl;


import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.core.dto.PageDto;
import com.qf.core.vo.PageResult;
import com.qf.core.vo.R;
import com.qf.device.entity.Device;
import com.qf.device.mapper.DeviceMapper;
import com.qf.login.entity.Tenant;
import com.qf.product.entity.Product;
import com.qf.product.mapper.ProductMapper;
import com.qf.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 产品表 服务实现类
 * </p>
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private DeviceMapper deviceMapper;
    //产品添加
    @Override
    public R add(Product product) {
        if (product == null) {
            return R.fail();
        }
        product.setDeleted(0);
        product.setPassword(SecureUtil.md5("123456"));
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        product.setTenantId(IdWorker.getIdStr());
        String key = "product"+"_"+product.getAreaCode()+"_"+IdWorker.getIdStr();
        product.setProductKey(key);
        product.setUsername(key);
        this.baseMapper.insert(product);

        return R.ok();
    }

   //分页查询
    @Override
    public PageResult<Product> page(PageDto<Product> pageDto) {
        Tenant tenant = (Tenant) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //设置分页
        IPage<Product> page = new Page<>(pageDto.getNow(), pageDto.getSize());
        //产品查询没有条件

        //查询分页信息
        QueryWrapper<Product> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(Product::getTenantId,tenant.getId());
        IPage<Product> pageInfo = this.baseMapper.selectPage(page, queryWrapper);


        PageResult pageResult = new PageResult();
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setList(pageInfo.getRecords());

        return pageResult;

    }

   //产品删除
    @Override
    public R deleteById(Product product) {
        if (product == null) {
            return R.fail();
        }
        QueryWrapper<Device> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(Device::getProductId,product.getId());
        List<Device> deviceList = deviceMapper.selectList(queryWrapper);
        if (deviceList != null) {
            return new R(false,400,"无法删除，该产品绑定了设备，请删除设备再删除产品",null);
        }
        int i = this.baseMapper.deleteById(product.getId());
        if (i != 1){
            return R.fail();
        }
        return R.ok();

    }

    @Override
    public List<Product> selectAllById() {
        Tenant tenant = (Tenant) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        QueryWrapper<Product> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(Product::getTenantId,tenant.getId());
        List<Product> products = productMapper.selectList(queryWrapper);
        return products;
    }
}
