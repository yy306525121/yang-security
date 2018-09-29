package cn.codeyang.demo.security;

import cn.codeyang.authorize.RbacService;
import cn.codeyang.core.authroize.AuthorizeConfigProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import javax.annotation.Priority;

@Component
@Order(Integer.MAX_VALUE)
public class DemoAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Autowired
    private RbacService rbacService;
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
//        config
//                .antMatchers("/user")
//                .hasRole("ADMIN");
//        config.anyRequest().access("@rbacService.hasPermission(request, authentication)");
        config.antMatchers("/user").access("@rbacService.hasPermission(request, authentication)");
    }
}
