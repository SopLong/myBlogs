package com.soplong.bolgs.pojo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by SopLong on 2019/8/18.
 */
@Data
public class SysCode implements Serializable {

    private Integer id;

    /**
     * 类型编码
     */
    private String type;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 字典编码
     */
    private String itemCode;

    /**
     * 字典名称
     */
    private String itemName;

    /**
     * 删除标记
     */
    private Integer delFlag;
}
