package com.qf.area.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 行政区划表
 * </p>
 *
 *
 *  
 */
@Getter
@Setter
@TableName("qf_area")
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 行政区划代码
     */
    @TableId(value = "code")
    private Integer code;

    /**
     * 名字
     */
    private String name;

    /**
     * 等级:1-省级;2-地级市;3-区/县;4-乡/镇
     */
    private Byte level;

    /**
     * 类型:1-省;2-自治区;3-直辖市;4-特别行政区;5-地级市;6-地区;7-自治州;8-盟;9-市辖区;10-县;11- 县级市;12-自治县;13-旗;14-自治旗;15-特区;16-林区
     */
    private Byte type;

    /**
     * 简称
     */
    private String abname;

    /**
     * 所属行政区划代码
     */
    private Integer pid;

    /**
     * 所属行政区划名字
     */
    private String pname;

    /**
     * 备注
     */
    private String note;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
