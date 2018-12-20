package com.example.ERP.config;

import com.example.ERP.controller.DepController;
import com.example.ERP.shiro.MyRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Covet on 2018/12/19.
 */
@Configuration
public class ShiroConfig {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    // 默认情况下bean的名称和方法名称相同，你也可以使用name属性来指定
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        logger.error("================ShiroFilterFactoryBean创建了==================");
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        // 装配securityManager
        shiroFilter.setSecurityManager(securityManager);
        // 配置登录页面
        shiroFilter.setLoginUrl("/index/jsp");
        // 登录后成功的页面
        shiroFilter.setSuccessUrl("/jsp/success.jsp");
        // 未经授权的页面
        shiroFilter.setUnauthorizedUrl("/jsp/unauthorized.jsp");

        // <!-- 具体配置需要拦截哪些 URL, 以及访问对应的 URL 时使用 Shiro 的什么 Filter 进行拦截. -->
        Map<String, String> filterChainDefinitionMap = new HashMap<>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/index.jsp", "anon");
        filterChainDefinitionMap.put("/jsp/success.jsp", "anon");
        filterChainDefinitionMap.put("/jsp/fail.jsp", "anon");
        filterChainDefinitionMap.put("/jsp/user.jsp", "roles[user]");
        filterChainDefinitionMap.put("/jsp/admin.jsp", "roles[admin]");
        filterChainDefinitionMap.put("/logout", "logout");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilter;
    }


    @Bean
    public DefaultWebSecurityManager securityManager(MyRealm myRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 自定义ream
        securityManager.setRealm(myRealm);
        securityManager.setSessionMode("native");
        return securityManager;
    }

}