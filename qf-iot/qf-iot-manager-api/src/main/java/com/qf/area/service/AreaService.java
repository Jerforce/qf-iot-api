package com.qf.area.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.area.entity.Area;
import com.qf.core.vo.PageDTO;
import com.qf.core.vo.PageResult;

/**
 * <p>
 * 行政区划表 服务类
 * </p>
 *
 * 
 *  
 */
public interface AreaService extends IService<Area> {

    PageResult page(PageDTO<Area> pageDTO);
}
