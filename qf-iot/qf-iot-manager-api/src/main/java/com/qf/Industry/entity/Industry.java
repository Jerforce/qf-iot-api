package com.qf.Industry.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/*
行业管理属性表
 * Jerforce
 *  2023-11-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("qf_industry")
public class Industry implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id")
    private Long id;
    //     行业名称
    private String name;
    //     创建时间
    private Date createTime;

}
