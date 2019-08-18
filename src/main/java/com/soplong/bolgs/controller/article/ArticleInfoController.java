package com.soplong.bolgs.controller.article;

import com.soplong.bolgs.pojo.system.ResultData;
import com.soplong.bolgs.service.article.ArticleContentService;
import com.soplong.bolgs.service.article.ArticleInfoService;
import com.soplong.bolgs.pojo.article.dto.ArticleInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by SopLong on 2019/8/18.
 */
@RestController
@RequestMapping("article")
public class ArticleInfoController {

    @Autowired
    private ArticleInfoService articleInfoService;
    @Autowired
    private ArticleContentService articleContentService;

    /**
     * 新增博客文章
     * @param articleInfoDto
     * @return
     */
    @PostMapping("addArticle")
    public ResultData addArticle(@RequestBody ArticleInfoDto articleInfoDto){

        return new ResultData();
    }
}
