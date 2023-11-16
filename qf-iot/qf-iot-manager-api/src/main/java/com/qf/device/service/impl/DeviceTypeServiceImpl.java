package com.qf.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.Industry.mapper.IndustryMapper;
import com.qf.core.vo.PageDTO;
import com.qf.core.vo.PageResult;
import com.qf.device.entity.DeviceType;
import com.qf.device.mapper.DeviceTypeMapper;
import com.qf.device.service.DeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 设备类型表 服务实现类
 * </p>
 *
 * Jerforce
 *  2023-11-26
 */
@Service
public class DeviceTypeServiceImpl extends ServiceImpl<DeviceTypeMapper, DeviceType> implements DeviceTypeService {

    @Autowired
    private DeviceTypeMapper deviceTypeMapper;
    @Autowired
    private IndustryMapper industryMapper;
    @Override
    public PageResult page(PageDTO<DeviceType> pageDto) {
        QueryWrapper<DeviceType> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(pageDto.getWhere().getName())) {
            queryWrapper.lambda().like(DeviceType::getName, pageDto.getWhere().getName());
        }
        if (!StringUtils.isEmpty(pageDto.getWhere().getIndustryId())) {
            queryWrapper.lambda().eq(DeviceType::getIndustryId, pageDto.getWhere().getIndustryId());
        }
        if (!StringUtils.isEmpty(pageDto.getWhere().getDeviceType())) {
            queryWrapper.lambda().eq(DeviceType::getDeviceType, pageDto.getWhere().getDeviceType());
        }
        IPage<DeviceType> iPage = new Page<>();
        iPage.setSize(pageDto.getSize());
        iPage.setCurrent(pageDto.getNow());
        IPage<DeviceType> page = deviceTypeMapper.selectPage(iPage, queryWrapper);
        PageResult<DeviceType> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        for (DeviceType deviceType : page.getRecords()) {
            deviceType.setIndustryName(industryMapper.selectById(deviceType.getIndustryId()).getName());
        }
        pageResult.setList(page.getRecords());
        return pageResult;
    }
}
