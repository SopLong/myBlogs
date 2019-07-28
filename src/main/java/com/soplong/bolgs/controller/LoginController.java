package com.soplong.bolgs.controller;

import com.soplong.bolgs.pojo.User;
import com.soplong.bolgs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by SopLong on 2019/7/28.
 */
@RestController
@RequestMapping("LoginController")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public User login(){
        User tblUser = userService.selectById(1);
        return tblUser;
    }
}
