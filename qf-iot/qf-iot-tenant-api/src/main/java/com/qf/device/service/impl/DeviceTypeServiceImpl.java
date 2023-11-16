package com.qf.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.core.vo.R;
import com.qf.device.entity.DeviceType;
import com.qf.device.mapper.DeviceTypeMapper;
import com.qf.device.service.DeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 设备类型表 服务实现类
 * </p>
 *
 * @author
 *  2023-11-26
 */
@Service
public class DeviceTypeServiceImpl extends ServiceImpl<DeviceTypeMapper, DeviceType> implements DeviceTypeService {
    @Autowired
    private DeviceTypeMapper deviceTypeMapper;

    @Override
    public R<List<DeviceType>> selectAll() {
        QueryWrapper<DeviceType> queryWrapper = new QueryWrapper<>();
        List<DeviceType> deviceTypes = deviceTypeMapper.selectList(queryWrapper);
        return new R<List<DeviceType>>(true,0,"success",deviceTypes);
    }
}
