package com.soplong.bolgs.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.soplong.bolgs.pojo.article.ArticleInfo;
import com.soplong.bolgs.service.article.ArticleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("front")
public class FrontController {
    @Autowired
    private ArticleInfoService articleInfoService;

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/index");
        Map<String,Object> map = new HashMap<>();
        List<ArticleInfo> articleInfos = articleInfoService.selectList(new EntityWrapper<ArticleInfo>().eq("del_flag", 0));
        map.put("data",articleInfos);
        map.put("pageNum",Math.rint(articleInfos.size()/10));
        modelAndView.addObject("aracleInfo",map);
        return modelAndView;
    }

    @GetMapping("/singlePost")
    public String singlePost() {
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
