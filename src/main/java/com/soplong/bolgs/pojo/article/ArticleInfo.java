package com.soplong.bolgs.pojo.article;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by SopLong on 2019/8/18.
 */
@Data
public class ArticleInfo implements Serializable {

    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章简介
     */
    private String summary;

    /**
     * 是否置顶,0为否,1为是
     */
    private Integer isTop;

    /**
     * 文章访问量
     */
    private Integer traffic;

    private Date createtime;

    private Date updatetime;

    /**
     * 删除标记 【0】正常 【1】删除
     */
    private Integer delFlag;

    /**
     * 图片地址
     */
    private String imgUrl;
}
