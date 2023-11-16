package com.qf.product.controller;

import com.qf.core.dto.PageDto;
import com.qf.core.vo.R;
import com.qf.product.entity.Product;
import com.qf.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * <p>
 * 产品表 前端控制器
 * </p>
 */
@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 产品添加接口
     * @param product
     * @return
     */
    @PreAuthorize("hasAuthority('产品添加')")
    @PostMapping("add")
    public R add(@RequestBody Product product){
        return productService.add(product);
    }

    /**
     * 分页查询接口
     * @param pageDto
     * @return
     */
    @PreAuthorize("hasAuthority('产品查询')")
    @PostMapping("page")
    public R page(@RequestBody PageDto<Product> pageDto){
        return R.ok(productService.page(pageDto));
    }

    /**
     * 产品删除
     * @param product
     * @return
     */
    @PreAuthorize("hasAuthority('产品删除')")
    @PostMapping("delete")
    public R delete(@RequestBody Product product){
        return productService.deleteById(product);
    }

}
