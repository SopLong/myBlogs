package com.soplong.bolgs.constant.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by SopLong on 2019/8/3.
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //以下是过滤链，按顺序过滤，所以/**需要放最后
        //开放的静态资源
        //anon. 配置不会被拦截的请求，auth配置需要认证的请求 顺序判断
        filterChainDefinitionMap.put("/favicon.ico", "anon");//网站图标
        filterChainDefinitionMap.put("/LoginController/**", "anon");
        filterChainDefinitionMap.put("/workPoint/**", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager(myRealm());
        return defaultWebSecurityManager;
    }

    @Bean
    public MyShiroRealm myRealm() {
        MyShiroRealm myRealm = new MyShiroRealm();
        return myRealm;
    }
}
