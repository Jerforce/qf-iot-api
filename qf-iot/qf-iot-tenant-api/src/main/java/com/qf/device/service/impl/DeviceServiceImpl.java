package com.qf.device.service.impl;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.core.dto.PageDto;
import com.qf.core.vo.PageResult;
import com.qf.core.vo.R;
import com.qf.device.entity.Device;
import com.qf.device.entity.DeviceTopic;
import com.qf.device.mapper.DeviceMapper;
import com.qf.device.mapper.DeviceTopicMapper;
import com.qf.device.mapper.DeviceTypeMapper;
import com.qf.device.service.DeviceService;
import com.qf.device.vo.DetailsVo;
import com.qf.login.entity.Tenant;
import com.qf.product.entity.Product;
import com.qf.product.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 设备表 服务实现类
 * </p>
 *
 * wang
 *  2023-11-26
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private DeviceTypeMapper deviceTypeMapper;
    @Autowired
    private DeviceTopicMapper deviceTopicMapper;

    @Override
    public PageResult page(PageDto<Product> pageDto) {
        Tenant tenant = (Tenant) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        QueryWrapper<Device> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Device::getTenantId, tenant.getId());
        if (!StringUtils.isEmpty(pageDto.getWhere().getId())) {
            queryWrapper.lambda().eq(Device::getProductId, pageDto.getWhere().getId());
        }
        IPage<Device> iPage = new Page<>();
        iPage.setSize(pageDto.getSize());
        iPage.setCurrent(pageDto.getNow());
        IPage<Device> page = deviceMapper.selectPage(iPage, queryWrapper);
        PageResult<Device> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        for (Device device : page.getRecords()) {
            device.setProductName(productMapper.selectById(device.getProductId()).getName());
            device.setTypeName(deviceTypeMapper.selectById(device.getDeviceTypeId()).getName());
        }
        pageResult.setList(page.getRecords());
        return pageResult;
    }

    @Override
    public R insert(Device device) {
        Tenant tenant = (Tenant) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SnowflakeGenerator snowflakeGenerator = new SnowflakeGenerator();
        Long id = snowflakeGenerator.next();
        device.setId(id);
        device.setDeviceKey("device_"+snowflakeGenerator.next());
        device.setCreateTime(new Date());
        device.setUpdateTime(new Date());
        device.setDeleted(0);
        device.setTenantId(tenant.getId());
        device.setGroupId(new Long(0));
        device.setActive(1);
        device.setDeviceSecret("6e1d010674ad4656b2b0722dcbf4403c");
        deviceMapper.insert(device);
        return new R(true, 0, "success", null);
    }

    @Override
    public R update(Device device) {
        deviceMapper.insert(device);
        return new R(true, 0, "success", null);
    }

    @Override
    public R deleteById(Long deviceId) {
        deviceMapper.deleteById(deviceId);
        return new R(true, 0, "success", null);
    }

    @Override
    public R selectById(String id) {
        Device device = deviceMapper.selectById(id);
        device.setProductName(productMapper.selectById(device.getProductId()).getName());
        device.setTypeName(deviceTypeMapper.selectById(device.getDeviceTypeId()).getName());
        Product product = productMapper.selectById(device.getProductId());
        QueryWrapper<DeviceTopic> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(DeviceTopic::getDeviceKey,device.getDeviceKey());
        List<DeviceTopic> deviceTopics = deviceTopicMapper.selectList(queryWrapper);
        DetailsVo detailsVo=new DetailsVo(device,product,deviceTopics);
        return new R(true,0,"success",detailsVo);
    }
}
