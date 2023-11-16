package com.qf.product.controller;

import com.qf.area.entity.Area;
import com.qf.area.service.AreaService;
import com.qf.core.vo.PageDTO;
import com.qf.core.vo.PageResult;
import com.qf.core.vo.R;
import com.qf.product.entity.Product;
import com.qf.product.service.ProductService;
import com.qf.product.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 产品表 前端控制器
 * </p>
 *
 * 
 *  2023-11-30
 */
@RestController
@RequestMapping("/product")
@CrossOrigin
@PreAuthorize("hasAuthority('sys:product:select')")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private AreaService areaService;
    @RequestMapping("page")
    public R page(@RequestBody PageDTO<Product> pageDto) {
        PageResult<Product> aPage = productService.page(pageDto);
        List<Area> list = areaService.list();
        ProductVo productVo=new ProductVo(aPage,list);
        return new R(true, 0, "success",productVo);
    }






}
