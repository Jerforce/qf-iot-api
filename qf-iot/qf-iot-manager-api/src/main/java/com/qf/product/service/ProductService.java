package com.qf.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.core.vo.PageDTO;
import com.qf.core.vo.PageResult;
import com.qf.product.entity.Product;

/**
 * <p>
 * 产品表 服务类
 * </p>
 *
 *
 *  2023-11-30
 */
public interface ProductService extends IService<Product> {
    PageResult page(PageDTO<Product> pageDTO);
}
