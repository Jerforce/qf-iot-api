package com.qf.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.core.dto.PageDto;
import com.qf.core.vo.PageResult;
import com.qf.core.vo.R;
import com.qf.device.entity.Device;
import com.qf.product.entity.Product;

/**
 * <p>
 * 设备表 服务类
 * </p>
 *
 * wang
 *  2023-11-26
 */
public interface DeviceService extends IService<Device> {
    PageResult page(PageDto<Product> pageDto);
    R insert(Device device);
    R update(Device device);
    R deleteById(Long deviceId);
    R selectById(String id);
}
