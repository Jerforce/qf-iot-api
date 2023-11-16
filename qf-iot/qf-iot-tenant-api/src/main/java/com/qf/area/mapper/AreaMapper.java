package com.qf.area.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.area.entity.Area;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AreaMapper extends BaseMapper<Area> {

    /**
     * 查询所有省市区
     */
    @Select("select * from qf_area")
    public List<Area> selectAll();
}