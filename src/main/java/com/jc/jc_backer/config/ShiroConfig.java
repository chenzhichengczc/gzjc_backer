package com.jc.jc_backer.config;

import com.jc.jc_backer.realm.AdminShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Charles Chan
 * @Date: 2019/3/17 14:21
 * @Version 1.0
 * 初始化Shiro
 */
@Configuration
public class ShiroConfig {

    //将验证方式注入Shiro容器
    @Bean
    public AdminShiroRealm ShiroConfig(){
        AdminShiroRealm realm = new AdminShiroRealm();
        return realm;
    }

    //权限管理,配置主要是Realm的权限认证
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(new AdminShiroRealm());
        return  securityManager;
    }

    //ShiroFilter工厂,设置对应过滤跳转
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> map = new HashMap<String, String>();
        //登出
        map.put("/logout","logout");

        //释放静态资源
        map.put("/static/**","anon");

        //对所有用户认证
        //map.put("/**","authc");
        //登录
        shiroFilterFactoryBean.setLoginUrl("/page/index");
        //首页
        shiroFilterFactoryBean.setSuccessUrl("/page/admin-index");
        //错误页面，认证不通过跳转
        /*shiroFilterFactoryBean.setUnauthorizedUrl("/page/ad");*/
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //加入注解使用
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
