package com.qf.device.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.qf.Industry.entity.Industry;
import com.qf.Industry.service.IndustryService;
import com.qf.core.vo.PageDTO;
import com.qf.core.vo.PageResult;
import com.qf.core.vo.R;
import com.qf.device.entity.DeviceType;
import com.qf.device.service.DeviceTypeService;
import com.qf.device.vo.DeviceTypeVo;
import com.qf.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 设备类型表 前端控制器
 * </p>
 *
 * Jerforce
 *  2023-11-26
 */
@RestController
@RequestMapping("/device-type")
@CrossOrigin
@PreAuthorize("hasAuthority('sys:deviceType:select')")
public class DeviceTypeController {

    @Autowired
    private DeviceTypeService deviceTypeService;
    @Autowired
    private IndustryService industryService;
    @RequestMapping("page")
    public R page(@RequestBody PageDTO<DeviceType> pageDto) {
        PageResult<DeviceType> aPage = deviceTypeService.page(pageDto);
        List<Industry> list = industryService.list();
        DeviceTypeVo deviceTypeVo=new DeviceTypeVo(aPage,list);
        return new R(true, 0, "success",deviceTypeVo);
    }

    @RequestMapping("/save")
    public R save(@RequestBody DeviceType deviceType) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        deviceType.setId(IdWorker.getId());
        deviceType.setCreateTime(new Date());
        deviceType.setUpdateTime(new Date());
        deviceTypeService.save(deviceType);
        return new R(true,0,"success",null);
    }

    @RequestMapping("/gotoUpd/{typeId}")
    public R gotoUpd(@PathVariable String typeId) {
        DeviceType deviceType = deviceTypeService.getById(typeId);
        return new R(true,0,"success",deviceType);
    }
    @RequestMapping("/update")
    public R update(@RequestBody DeviceType deviceType) {
        deviceTypeService.updateById(deviceType);
        return new R(true,0,"success",null);
    }

}
