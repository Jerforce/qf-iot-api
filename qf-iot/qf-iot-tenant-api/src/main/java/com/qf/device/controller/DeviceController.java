package com.qf.device.controller;

import com.qf.core.dto.PageDto;
import com.qf.core.vo.PageResult;
import com.qf.core.vo.R;
import com.qf.device.entity.Device;
import com.qf.device.service.DeviceService;
import com.qf.device.service.DeviceTypeService;
import com.qf.device.vo.DeviceVo;
import com.qf.product.entity.Product;
import com.qf.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * <p>
 * 设备表 前端控制器
 * </p>
 *
 * wang
 *  2023-11-26
 */
@RestController
@RequestMapping("/device")
@CrossOrigin
public class DeviceController {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private ProductService productService;
    @Autowired
    private DeviceTypeService deviceTypeService;


    @RequestMapping("/gotoAdd")
    public R gotoAdd() {
        return deviceTypeService.selectAll();
    }

    @RequestMapping("page")
    @PreAuthorize("hasAuthority('设备查询')")
    public R page(@RequestBody PageDto<Product> pageDto) {
        PageResult<Device> aPage = deviceService.page(pageDto);
        List<Product> productList = productService.selectAllById();
        DeviceVo deviceVo = new DeviceVo(productList, aPage);
        return new R(true, 0, "success", deviceVo);
    }

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('设备添加')")
    public R add(@RequestBody Device device) {
        R r = deviceService.insert(device);
        return r;
    }

    @RequestMapping("/details/{id}")
    @PreAuthorize("hasAuthority('设备详情')")
    public R details(@PathVariable String id) {
        R r = deviceService.selectById(id);
        return r;
    }

    @PreAuthorize("hasAuthority('设备删除')")
    @RequestMapping("/delById/{deviceId}")
    public R delById(@PathVariable Long deviceId) {
        R r = deviceService.deleteById(deviceId);
        return r;
    }
}
