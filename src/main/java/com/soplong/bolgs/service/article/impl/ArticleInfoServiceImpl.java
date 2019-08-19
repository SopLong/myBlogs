package com.soplong.bolgs.service.article.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.soplong.bolgs.mapper.article.ArticleInfoMapper;
import com.soplong.bolgs.pojo.article.ArticleInfo;
import com.soplong.bolgs.service.article.ArticleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by SopLong on 2019/8/18.
 */

@Service
public class ArticleInfoServiceImpl extends ServiceImpl<ArticleInfoMapper, ArticleInfo> implements ArticleInfoService {

    @Autowired
    private ArticleInfoMapper articleInfoMapper;

    @Override
    public ArticleInfo addArticle(ArticleInfo articleInfo) {
        this.insert(articleInfo);
        return articleInfo;
    }

    @Override
    public Page getArticleList(Map<String, String> reqMap,Page page) {
        return page.setRecords(articleInfoMapper.getArticleList(reqMap,page));
    }
}
