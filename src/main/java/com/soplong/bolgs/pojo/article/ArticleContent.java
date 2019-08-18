package com.soplong.bolgs.pojo.article;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by SopLong on 2019/8/18.
 */
@Data
public class ArticleContent implements Serializable {

    private Integer id;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章ID
     */
    private String articleId;
}
