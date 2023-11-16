package com.qf.device.controller;

import com.qf.core.vo.PageDTO;
import com.qf.core.vo.PageResult;
import com.qf.core.vo.R;
import com.qf.device.entity.Device;
import com.qf.device.entity.DeviceType;
import com.qf.device.service.DeviceService;
import com.qf.device.service.DeviceTypeService;
import com.qf.device.vo.DeviceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 设备表 前端控制器
 * </p>
 *
 * Jerforce
 *  2023-11-26
 */
@RestController
@RequestMapping("/device")
@CrossOrigin
@PreAuthorize("hasAuthority('sys:device:select')")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceTypeService deviceTypeService;

    @RequestMapping("page")
    public R page(@RequestBody PageDTO<Device> pageDto) {
        PageResult<Device> aPage = deviceService.page(pageDto);
        List<DeviceType> list = deviceTypeService.list();
        DeviceVo deviceVo=new DeviceVo(aPage,list);
        return new R(true, 0, "success",deviceVo);
    }


}
