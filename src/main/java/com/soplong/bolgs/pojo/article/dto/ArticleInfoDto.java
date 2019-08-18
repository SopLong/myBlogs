package com.soplong.bolgs.pojo.article.dto;

import com.soplong.bolgs.pojo.article.ArticleInfo;
import lombok.Data;

/**
 * Created by SopLong on 2019/8/18.
 */
@Data
public class ArticleInfoDto extends ArticleInfo {
    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章ID
     */
    private String articleId;
}
