package com.qf.area.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.area.entity.Area;
import com.qf.core.vo.R;

/**
 * <p>
 * 行政区划表 服务类
 * </p>
 *
 */
public interface AreaService extends IService<Area> {



     //查询所有省市区

    public R selectAll();
}
