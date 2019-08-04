package com.soplong.bolgs.constant.shiro;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.soplong.bolgs.pojo.blog.User;
import com.soplong.bolgs.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by SopLong on 2019/8/3.
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 登录验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        System.out.println(String.valueOf(token.getPassword()));
        User user = userService.selectOne(new EntityWrapper<User>().eq("name", token.getUsername()).eq("password",String.valueOf(token.getPassword())));

        if(null != user){
            return new SimpleAuthenticationInfo(user.getName(), user.getPassword(), getName());
        }else{
            throw new AuthenticationException();
        }
    }
}
