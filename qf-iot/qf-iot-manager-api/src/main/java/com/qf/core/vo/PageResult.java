package com.qf.core.vo;

import lombok.Data;

import java.util.List;
@Data
public class PageResult <T>{

    private Long total;
    //总页数
    private Long pages;
    //页数
    private Integer pagenow;

    private Integer size;

    private List<T> list;


}
