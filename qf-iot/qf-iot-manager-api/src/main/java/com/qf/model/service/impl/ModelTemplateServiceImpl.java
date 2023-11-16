package com.qf.model.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.core.vo.PageDTO;
import com.qf.core.vo.PageResult;
import com.qf.device.mapper.DeviceTypeMapper;
import com.qf.model.entity.ModelTemplate;
import com.qf.model.mapper.ModelTemplateMapper;
import com.qf.model.service.ModelTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 物模型模板表 服务实现类
 * </p>
 *
 * 
 *  2023-11-01
 */
@Service
public class ModelTemplateServiceImpl extends ServiceImpl<ModelTemplateMapper, ModelTemplate> implements ModelTemplateService {

    @Autowired
    private ModelTemplateMapper modelTemplateMapper;
    @Autowired
    private DeviceTypeMapper deviceTypeMapper;
    @Override
    public PageResult page(PageDTO<ModelTemplate> pageDto) {
        QueryWrapper<ModelTemplate> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(pageDto.getWhere().getPropertyName())) {
            queryWrapper.lambda().like(ModelTemplate::getPropertyName, pageDto.getWhere().getPropertyName());
        }
        if (!StringUtils.isEmpty(pageDto.getWhere().getDeviceTypeId())) {
            queryWrapper.lambda().eq(ModelTemplate::getDeviceTypeId, pageDto.getWhere().getDeviceTypeId());
        }
        if (!StringUtils.isEmpty(pageDto.getWhere().getModelType())) {
            queryWrapper.lambda().eq(ModelTemplate::getModelType, pageDto.getWhere().getModelType());
        }
        IPage<ModelTemplate> iPage = new Page<>();
        iPage.setSize(pageDto.getSize());
        iPage.setCurrent(pageDto.getNow());
        IPage<ModelTemplate> page = modelTemplateMapper.selectPage(iPage, queryWrapper);
        PageResult<ModelTemplate> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        for (ModelTemplate modelTemplate : page.getRecords()) {
            modelTemplate.setDeviceTypeName(deviceTypeMapper.selectById(modelTemplate.getDeviceTypeId()).getName());
        }
        pageResult.setList(page.getRecords());
        return pageResult;
    }
}
