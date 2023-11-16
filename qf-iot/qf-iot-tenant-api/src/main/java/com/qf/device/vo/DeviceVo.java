package com.qf.device.vo;

import com.qf.core.vo.PageResult;
import com.qf.device.entity.Device;
import com.qf.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceVo {
    private List<Product> productList;
    private PageResult<Device> pageResult;
}
