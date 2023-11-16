package com.qf.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.core.vo.PageDTO;
import com.qf.core.vo.PageResult;
import com.qf.device.entity.Device;
import com.qf.device.mapper.DeviceMapper;
import com.qf.device.mapper.DeviceTypeMapper;
import com.qf.device.service.DeviceService;
import com.qf.product.mapper.ProductMapper;
import com.qf.tenant.mapper.TenantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 设备表 服务实现类
 * </p>
 *
 * Jerforce
 *  2023-11-26
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private DeviceTypeMapper deviceTypeMapper;
    @Autowired
    private TenantMapper tenantMapper;
    @Autowired
    private ProductMapper productMapper;
    @Override
    public PageResult page(PageDTO<Device> pageDto) {
        QueryWrapper<Device> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(pageDto.getWhere().getName())) {
            queryWrapper.lambda().like(Device::getName, pageDto.getWhere().getName());
        }
        if (!StringUtils.isEmpty(pageDto.getWhere().getDeviceKey())) {
            queryWrapper.lambda().like(Device::getDeviceKey, pageDto.getWhere().getDeviceKey());
        }
        if (!StringUtils.isEmpty(pageDto.getWhere().getDeviceTypeId())) {
            queryWrapper.lambda().eq(Device::getDeviceTypeId, pageDto.getWhere().getDeviceTypeId());
        }
        if (!StringUtils.isEmpty(pageDto.getWhere().getActive())) {
            queryWrapper.lambda().eq(Device::getActive, pageDto.getWhere().getActive());
        }
        IPage<Device> iPage = new Page<>();
        iPage.setSize(pageDto.getSize());
        iPage.setCurrent(pageDto.getNow());
        IPage<Device> page = deviceMapper.selectPage(iPage, queryWrapper);
        PageResult<Device> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        for (Device device : page.getRecords()) {
            device.setTenantName(tenantMapper.selectById(device.getTenantId()).getName());
            device.setProductName(productMapper.selectById(device.getProductId()).getName());
            device.setTypeName(deviceTypeMapper.selectById(device.getDeviceTypeId()).getName());
        }
        pageResult.setList(page.getRecords());
        return pageResult;
    }
}
