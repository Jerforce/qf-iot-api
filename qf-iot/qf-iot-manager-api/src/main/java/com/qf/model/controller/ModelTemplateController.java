package com.qf.model.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.qf.core.vo.PageDTO;
import com.qf.core.vo.PageResult;
import com.qf.core.vo.R;
import com.qf.device.entity.DeviceType;
import com.qf.device.service.DeviceTypeService;
import com.qf.model.entity.ModelTemplate;
import com.qf.model.service.ModelTemplateService;
import com.qf.model.vo.ModelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 物模型模板表 前端控制器
 * </p>
 *
 * @author
 *  2023-11-01
 */
@RestController
@RequestMapping("/model-template")
@CrossOrigin
@PreAuthorize("hasAuthority('sys:modelTemplate:select')")
public class ModelTemplateController {

    @Autowired
    private ModelTemplateService modelTemplateService;
    @Autowired
    private DeviceTypeService deviceTypeService;

    @RequestMapping("page")
    public R page(@RequestBody PageDTO<ModelTemplate> pageDto) {
        PageResult<ModelTemplate> aPage = modelTemplateService.page(pageDto);
        List<DeviceType> list = deviceTypeService.list();
        ModelVo modelVo = new ModelVo(aPage, list);
        return new R(true, 0, "success", modelVo);
    }

    @RequestMapping("/save")
    public R save(@RequestBody ModelTemplate modelTemplate) {
        modelTemplate.setId(IdWorker.getId());
        modelTemplate.setCreateTime(new Date());
        modelTemplate.setUpdateTime(new Date());
        modelTemplateService.save(modelTemplate);
        return new R(true, 0, "success", null);
    }

    @RequestMapping("/gotoUpd/{typeId}")
    public R gotoUpd(@PathVariable String typeId) {
        ModelTemplate modelTemplate = modelTemplateService.getById(typeId);
        return new R(true, 0, "success", modelTemplate);
    }

    @RequestMapping("/update")
    public R update(@RequestBody ModelTemplate modelTemplate) {
        modelTemplateService.updateById(modelTemplate);
        return new R(true, 0, "success", null);
    }


}
