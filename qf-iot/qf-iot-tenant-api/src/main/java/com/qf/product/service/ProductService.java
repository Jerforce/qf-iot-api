package com.qf.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.core.dto.PageDto;
import com.qf.core.vo.PageResult;
import com.qf.core.vo.R;
import com.qf.product.entity.Product;

import java.util.List;

/**
 * <p>
 * 产品表 服务类
 * </p>
 */
public interface ProductService extends IService<Product> {
    /**
     * 产品添加
     */
    public R add(Product product);

    /**
     * 产品分页查询
     */
    public PageResult<Product> page(PageDto<Product> pageDto);

    /**
     * 产品删除
     */
    public R deleteById(Product product);
    List<Product> selectAllById();
}
