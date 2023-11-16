package com.qf.model.vo;

import com.qf.core.vo.PageResult;
import com.qf.device.entity.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelVo {
    private PageResult pageResult;
    private List<DeviceType> deviceTypeList;
}
