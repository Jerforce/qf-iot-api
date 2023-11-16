package com.qf.pck.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.core.vo.R;
import com.qf.pck.entity.PackageOrder;
import com.qf.pck.mapper.PackageOrderMapper;
import com.qf.pck.service.PackageOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * 
 *  2023-11-19
 */
@Service
public class PackageOrderServiceImpl extends ServiceImpl<PackageOrderMapper, PackageOrder> implements PackageOrderService {

    @Autowired
    private PackageOrderMapper packageOrderMapper;
    @Override
    public R all() {
        QueryWrapper<PackageOrder> queryWrapper = new QueryWrapper<>();
        List<PackageOrder> orderList = packageOrderMapper.selectList(queryWrapper);
        return new R(true, 0, "success", orderList);
    }
}
