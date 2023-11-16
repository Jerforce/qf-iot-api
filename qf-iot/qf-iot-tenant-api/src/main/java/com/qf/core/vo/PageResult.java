package com.qf.core.vo;

import lombok.Data;

import java.util.List;
@Data
public class PageResult <T>{

    private Long total;

    private List<T> list;


    public void setPages(long pages) {
    }
}
