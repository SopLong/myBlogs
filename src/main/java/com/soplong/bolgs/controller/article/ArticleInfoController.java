package com.soplong.bolgs.controller.article;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.soplong.bolgs.annotation.Log;
import com.soplong.bolgs.pojo.article.ArticleContent;
import com.soplong.bolgs.pojo.article.ArticleInfo;
import com.soplong.bolgs.pojo.system.ResultData;
import com.soplong.bolgs.service.article.ArticleContentService;
import com.soplong.bolgs.service.article.ArticleInfoService;
import com.soplong.bolgs.pojo.article.dto.ArticleInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by SopLong on 2019/8/18.
 */
@Slf4j
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
    @Log("新增博客")
    @PostMapping("addArticle")
    public ResultData addArticle(@RequestBody ArticleInfoDto articleInfoDto){
        ArticleInfo articleInfo = new ArticleInfo();
        BeanUtils.copyProperties(articleInfoDto,articleInfo);
        ArticleInfo article = articleInfoService.addArticle(articleInfo);
        ArticleContent articleContent = new ArticleContent();

        articleContent.setContent(articleInfoDto.getContent());
        articleContent.setArticleId(article.getId());
        articleContentService.addArticleContent(articleContent);
        return new ResultData();
    }

    /**
     * 查询博文列表
     * @param reqMap
     * @return
     */
    @PostMapping("getArticleList")
    public ResultData getArticleList(@RequestBody Map<String,String> reqMap){
        Page page = new Page();
        page.setSize(Integer.parseInt(reqMap.get("size")));
        page.setCurrent(Integer.parseInt(reqMap.get("page")));
        return new ResultData(articleInfoService.getArticleList(reqMap,page));
    }

    /**
     * 删除博客文章
     * @param id
     * @return
     */
    @GetMapping("deleteArticle/{id}")
    public ResultData deleteArticle(@PathVariable String id){
        articleInfoService.updateForSet("del_flag = 1",new EntityWrapper<ArticleInfo>().eq("id",id));
        return new ResultData();
    }
}
