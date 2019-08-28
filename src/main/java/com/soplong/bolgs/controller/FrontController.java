package com.soplong.bolgs.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.soplong.bolgs.pojo.article.ArticleContent;
import com.soplong.bolgs.pojo.article.ArticleInfo;
import com.soplong.bolgs.service.article.ArticleContentService;
import com.soplong.bolgs.service.article.ArticleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("front")
public class FrontController {
    @Autowired
    private ArticleInfoService articleInfoService;
    @Autowired
    private ArticleContentService articleContentServicel;

    @GetMapping("/index")
    public String index(Model model, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "1") Integer pageSize) {
        Map<String,String> map = new HashMap<>();
        Page page = new Page();
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        Page articlePage = articleInfoService.getArticleList(map, page);
        model.addAttribute("articleInfos", articlePage.getRecords());
        model.addAttribute("total", Math.rint(page.getTotal() / pageSize) == 0 ? 1 : Math.rint(page.getTotal() / pageSize));
        model.addAttribute("currentPage", pageNum);
        return "index";
    }

    @GetMapping("/singlePost")
    public String singlePost(Model model,@RequestParam Integer id) {
        ArticleInfo articleInfo = articleInfoService.selectById(id);
        ArticleContent articleContent = articleContentServicel.selectOne(new EntityWrapper<ArticleContent>().eq("article_id", id));
        model.addAttribute("detail",articleInfo);
        model.addAttribute("content",articleContent);
        return "single-post";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }

    @GetMapping("/link")
    public String link() {
        return "link";
    }

    @GetMapping("/search")
    public String search() {
        return "search";
    }

    @GetMapping("/archives")
    public String archives() {
        return "archives";
    }
}
