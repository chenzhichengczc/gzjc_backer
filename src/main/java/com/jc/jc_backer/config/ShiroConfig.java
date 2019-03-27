package com.jc.jc_backer.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.jc.jc_backer.realm.AdminShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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
public class ShiroConfig implements ApplicationContextAware {



    //权限管理,配置主要是Realm的权限认证
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(new AdminShiroRealm());
        return  securityManager;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    //ShiroFilter工厂,设置对应过滤跳转
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> map = new HashMap<String, String>();

        map.put("/admin/registeAdmin","anon");
        map.put("/admin/loginAdmin","anon");

        map.put("/page/register","anon");

        //登出 TODO
        map.put("/logout","logout");

        //释放静态资源
        map.put("/static/**","anon");

        //对所有用户认证
        map.put("/**","authc");
        //登录
        shiroFilterFactoryBean.setLoginUrl("/page/index");

        shiroFilterFactoryBean.setSuccessUrl("/page/admin-index");
        //错误页面，认证不通过跳转
        /*shiroFilterFactoryBean.setUnauthorizedUrl("/page/ad");*/
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }



    //将验证方式注入Shiro容器
    @Bean
    public AdminShiroRealm ShiroConfig(){
        AdminShiroRealm realm = new AdminShiroRealm();
        return realm;
    }

    //配置html标签
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    //加入注解使用
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 解决Shiro自定义Realm无法进行注入问题
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Realm bean = applicationContext.getBean(AdminShiroRealm.class);
        DefaultWebSecurityManager defaultWebSecurityManager = (DefaultWebSecurityManager)applicationContext.getBean(SecurityManager.class);
        defaultWebSecurityManager.setRealm(bean);
    }
}
