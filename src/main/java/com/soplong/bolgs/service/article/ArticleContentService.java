package com.soplong.bolgs.service.article;

import com.baomidou.mybatisplus.service.IService;
import com.soplong.bolgs.pojo.article.ArticleContent;

/**
 * Created by SopLong on 2019/8/18.
 */
public interface ArticleContentService extends IService<ArticleContent> {
    /**
     * 新增博客内容
     * @param articleContent
     */
    void addArticleContent(ArticleContent articleContent);
}
