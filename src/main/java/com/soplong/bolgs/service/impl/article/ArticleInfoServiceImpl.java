package com.soplong.bolgs.service.impl.article;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.soplong.bolgs.mapper.article.ArticleInfoMapper;
import com.soplong.bolgs.pojo.article.ArticleInfo;
import com.soplong.bolgs.service.article.ArticleInfoService;
import org.springframework.stereotype.Service;

/**
 * Created by SopLong on 2019/8/18.
 */

@Service
public class ArticleInfoServiceImpl extends ServiceImpl<ArticleInfoMapper, ArticleInfo> implements ArticleInfoService {
    @Override
    public ArticleInfo addArticle(ArticleInfo articleInfo) {
        this.insert(articleInfo);
        return articleInfo;
    }
}
