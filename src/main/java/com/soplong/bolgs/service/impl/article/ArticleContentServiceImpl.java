package com.soplong.bolgs.service.impl.article;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.soplong.bolgs.mapper.article.ArticleContentMapper;
import com.soplong.bolgs.pojo.article.ArticleContent;
import com.soplong.bolgs.service.article.ArticleContentService;
import org.springframework.stereotype.Service;

/**
 * Created by SopLong on 2019/8/18.
 */

@Service
public class ArticleContentServiceImpl extends ServiceImpl<ArticleContentMapper, ArticleContent> implements ArticleContentService {
    @Override
    public void addArticleContent(ArticleContent articleContent) {
        this.insert(articleContent);
    }
}
