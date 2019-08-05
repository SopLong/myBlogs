package com.soplong.bolgs.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by SopLong on 2019/8/4.
 */
@Data
public class WorkPointRecord implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String dateTime;

    private double allHours;

    private double normalWorkHours;

    private double normalOverworkHours;

    private double weekendWorkHours;

    private double weekendOverworkHours;

    private Date createtime;

    private Date updatetime;

    private Integer delFlag;

    /**
     * 是否是周末 【0】不是 【1】是
     */
    private Integer isWeek;
}
