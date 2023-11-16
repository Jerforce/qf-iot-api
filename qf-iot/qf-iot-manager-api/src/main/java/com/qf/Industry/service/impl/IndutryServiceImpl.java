package com.qf.Industry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.Industry.entity.Industry;
import com.qf.Industry.mapper.IndustryMapper;
import com.qf.Industry.service.IndustryService;
import com.qf.core.vo.PageDTO;
import com.qf.core.vo.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/*
行业管理 服务实现类
 * Jerforce
 *  2023-11-27
 */
@Service
public class IndutryServiceImpl extends ServiceImpl<IndustryMapper , Industry> implements IndustryService {

    public PageResult<Industry> page(PageDTO<Industry> pageDto) {

        //分页信息
        IPage<Industry> page = new Page<>(pageDto.getNow(), pageDto.getSize());

        //条件
        QueryWrapper<Industry> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(pageDto.getWhere().getName())){
            queryWrapper.lambda().like(Industry::getName,pageDto.getWhere().getName());
        }
        IPage<Industry> pageInfo = this.baseMapper.selectPage(page,queryWrapper);

        //封装分页结果
        PageResult<Industry> pageResult = new PageResult<>();
        pageResult.setPages(pageInfo.getPages());
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setList(pageInfo.getRecords());

        return pageResult;


    }
}
