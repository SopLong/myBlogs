package com.soplong.bolgs.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by SopLong on 2019/7/28.
 */
@Data
public class User implements Serializable {

    private Integer id;

    /**
     * 登录名
     */
    private String name;

    /**
     * 用户名
     */
    private String fullName;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 是否可用 【1】有效，【0】无效
     */
    private Integer isEffective;

    /**
     * 头像
     */
    private String profile;
}
