package com.soplong.bolgs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
    @RequestMapping("front")
public class FrontController {
    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("data", "<span style='color:red'>老王是吃货</span>");
        return modelAndView;
    }

    @GetMapping("/gustbook")
    public String gustbook() {
        return "gustbook";
    }

    @GetMapping("/detail")
    public String detail() {
        return "detail";
    }

    @GetMapping("/update")
    public String update() {
        return "update";
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
