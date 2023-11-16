package com.qf.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.core.vo.PageDTO;
import com.qf.core.vo.PageResult;
import com.qf.device.entity.DeviceType;
/**
 * <p>
 * 设备类型表 服务类
 * </p>
 *
 * Jerforce
 *  2023-11-19
 */
public interface DeviceTypeService extends IService<DeviceType> {

    PageResult page(PageDTO<DeviceType> pageDTO);
}
