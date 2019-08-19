package com.soplong.bolgs.mapper.article;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.soplong.bolgs.pojo.article.ArticleInfo;
import com.soplong.bolgs.pojo.article.dto.ArticleInfoDto;

import java.util.List;
import java.util.Map;

/**
 * Created by SopLong on 2019/8/18.
 */
public interface ArticleInfoMapper extends BaseMapper<ArticleInfo> {
    List<ArticleInfoDto> getArticleList(Map<String, String> reqMap, Page page);
}
