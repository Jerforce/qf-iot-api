package com.qf.area.controller;

import com.qf.area.service.AreaService;
import com.qf.core.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 行政区划表 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/area")
@CrossOrigin
public class AreaController {
    @Autowired
    private AreaService areaService;

    /**
     * 查询省市区接口
     * @return
     */
    @PostMapping("all")
    public R all(){
        R r = areaService.selectAll();
        return r;
    }




}
