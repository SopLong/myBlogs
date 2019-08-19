package com.soplong.bolgs.controller;

import com.soplong.bolgs.constant.ResultCode;
import com.soplong.bolgs.pojo.system.User;
import com.soplong.bolgs.pojo.system.ResultData;
import com.soplong.bolgs.service.system.impl.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by SopLong on 2019/7/28.
 */
@RestController
@RequestMapping("LoginController")
@ControllerAdvice
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ResultData login(@RequestBody User user){
        ResultData resultData = new ResultData();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(),user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            resultData.setCode(ResultCode.SUCCESS.code());
            resultData.setMsg("登陆成功");
        } catch (IncorrectCredentialsException e) {
            resultData.setMsg("密码错误");
            resultData.setCode(ResultCode.FAIL.code());
        } catch (LockedAccountException e) {
            resultData.setCode(ResultCode.FAIL.code());
            resultData.setMsg("该用户已被禁用");
        } catch (AuthenticationException e) {
            resultData.setCode(ResultCode.FAIL.code());
            resultData.setMsg("该用户不存在");
        }
        return resultData;
    }

    /**
     * 登出
     * @return
     */
    @GetMapping("logout")
    public ResultData logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ResultData();
    }
}
