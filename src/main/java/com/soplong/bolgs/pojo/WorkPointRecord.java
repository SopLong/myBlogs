package com.soplong.bolgs.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by SopLong on 2019/8/4.
 */
@Data
public class WorkPointRecord implements Serializable {

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
}
