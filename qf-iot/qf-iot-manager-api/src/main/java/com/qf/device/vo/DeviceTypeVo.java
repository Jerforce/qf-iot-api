package com.qf.device.vo;

import com.qf.Industry.entity.Industry;
import com.qf.core.vo.PageResult;
import com.qf.device.entity.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceTypeVo {
    private PageResult<DeviceType> pageResult;
    private List<Industry> industryList;
}
