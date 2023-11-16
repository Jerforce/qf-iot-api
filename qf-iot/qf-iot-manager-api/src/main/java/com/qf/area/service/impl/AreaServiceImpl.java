package com.qf.area.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.area.entity.Area;
import com.qf.area.mapper.AreaMapper;
import com.qf.area.service.AreaService;
import com.qf.core.vo.PageDTO;
import com.qf.core.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 行政区划表 服务实现类
 * </p>
 *
 * 
 *  
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {

    @Autowired
    private AreaMapper areaMapper;
    @Override
    public PageResult page(PageDTO<Area> pageDto) {
        QueryWrapper<Area> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(pageDto.getWhere().getCode())) {
            queryWrapper.lambda().like(Area::getCode, pageDto.getWhere().getCode());
        }
        if (!StringUtils.isEmpty(pageDto.getWhere().getName())) {
            queryWrapper.lambda().like(Area::getName, pageDto.getWhere().getName());
        }
        IPage<Area> iPage = new Page<>();
        iPage.setSize(pageDto.getSize());
        iPage.setCurrent(pageDto.getNow());
        IPage<Area> page = areaMapper.selectPage(iPage, queryWrapper);
        PageResult<Area> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        pageResult.setList(page.getRecords());
        return pageResult;
    }
}
