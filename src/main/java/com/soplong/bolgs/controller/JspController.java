package com.soplong.bolgs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
    @RequestMapping("front")
public class JspController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/gustbook")
    public String gustbook() {
        return "gustbook";
    }
}
