package com.qf.area.controller;

import com.qf.area.entity.Area;
import com.qf.area.service.AreaService;
import com.qf.core.vo.PageDTO;
import com.qf.core.vo.PageResult;
import com.qf.core.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * <p>
 * 行政区划表 前端控制器
 * </p>
 *
 *
 *  
 */
@RestController
@RequestMapping("/area")
@CrossOrigin
@PreAuthorize("hasAuthority('sys:area:select')")
public class AreaController {

@Autowired
private AreaService areaService;

    @RequestMapping("page")
    public R page(@RequestBody PageDTO<Area> pageDto) {
        PageResult<Area> aPage = areaService.page(pageDto);

        return new R(true, 0, "success",aPage);
    }




}
