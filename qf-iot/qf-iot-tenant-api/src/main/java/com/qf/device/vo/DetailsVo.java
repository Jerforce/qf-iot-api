package com.qf.device.vo;

import com.qf.device.entity.Device;
import com.qf.device.entity.DeviceTopic;
import com.qf.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailsVo {
    private Device device;
    private Product product;
    private List<DeviceTopic> deviceTopics;
}
