package com.qf.model.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.core.vo.PageDTO;
import com.qf.core.vo.PageResult;
import com.qf.model.entity.ModelTemplate;

/**
 * <p>
 * 物模型模板表 服务类
 * </p>
 *
 * 
 *  2023-11-01
 */
public interface ModelTemplateService extends IService<ModelTemplate> {
    PageResult page(PageDTO<ModelTemplate> pageDTO);
}
