package com.qf.product.vo;

import com.qf.area.entity.Area;
import com.qf.core.vo.PageResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVo {
    private PageResult pageResult;
    private List<Area> areaList;
}
