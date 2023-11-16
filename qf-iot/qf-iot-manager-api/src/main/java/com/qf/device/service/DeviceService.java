package com.qf.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.core.vo.PageDTO;
import com.qf.core.vo.PageResult;
import com.qf.device.entity.Device;

/**
 * <p>
 * 设备表 服务类
 * </p>
 *
 * Jerforce
 *  2023-11-19
 */
public interface DeviceService extends IService<Device> {
    PageResult page(PageDTO<Device> pageDTO);
}
