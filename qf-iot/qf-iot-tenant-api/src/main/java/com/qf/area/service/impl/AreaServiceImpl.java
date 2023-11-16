package com.qf.area.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.area.entity.Area;
import com.qf.area.mapper.AreaMapper;
import com.qf.area.service.AreaService;
import com.qf.core.vo.R;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 行政区划表 服务实现类
 * </p>
 *
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {

    /**
     * 查询所有省市区
     * @return
     */
    @Override
    public R selectAll() {
        List<Area> areas = this.baseMapper.selectAll();
        return R.ok(areas);
    }


}