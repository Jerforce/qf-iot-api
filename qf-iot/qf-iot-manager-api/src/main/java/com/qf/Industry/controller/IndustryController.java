package com.qf.Industry.controller;


import com.qf.Industry.entity.Industry;
import com.qf.Industry.service.impl.IndutryServiceImpl;
import com.qf.core.vo.PageDTO;
import com.qf.core.vo.PageResult;
import com.qf.core.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/*
行业管理  前端控制器
 * Jerforce
 *  2023-11-27
 */
@RestController
@CrossOrigin
@RequestMapping("industry")
@PreAuthorize("hasAuthority('sys:industry:select')")
public class IndustryController {

    @Autowired
    private IndutryServiceImpl indutryService;
     //行业条件分页查询
    @PostMapping("/page")
    public R page(@RequestBody PageDTO<Industry> pageDto){
        PageResult<Industry> pageResult = indutryService.page(pageDto);
        return R.ok(pageResult);
    }

    //行业添加
    @PostMapping("/save")
    public R save(@RequestBody Industry industry){


     indutryService.save(industry);

        return R.ok("添加成功");
    }
     //行业修改
    @PostMapping("/update")
    public R update(@RequestBody Industry industry){

        indutryService.updateById(industry);

        return R.ok("修改成功") ;
    }
    //行业删除
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable String id){

        indutryService.removeById(id);

        return R.ok("删除成功") ;
    }

}
