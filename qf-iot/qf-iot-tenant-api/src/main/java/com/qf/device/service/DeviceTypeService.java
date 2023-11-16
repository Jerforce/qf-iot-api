package com.qf.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.core.vo.R;
import com.qf.device.entity.DeviceType;

import java.util.List;

/**
 * <p>
 * 设备类型表 服务类
 * </p>
 *
 * @author
 *  2023-11-26
 */
public interface DeviceTypeService extends IService<DeviceType> {
     R<List<DeviceType>> selectAll();
}
