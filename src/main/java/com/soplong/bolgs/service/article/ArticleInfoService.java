package com.soplong.bolgs.service.article;

import com.baomidou.mybatisplus.service.IService;
import com.soplong.bolgs.pojo.article.ArticleInfo;

/**
 * Created by SopLong on 2019/8/18.
 */
public interface ArticleInfoService extends IService<ArticleInfo> {
    /**
     * 新增博客
     * @param articleInfo
     * @return
     */
    ArticleInfo addArticle(ArticleInfo articleInfo);
}
