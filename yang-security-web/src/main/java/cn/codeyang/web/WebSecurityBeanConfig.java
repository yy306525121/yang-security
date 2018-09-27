package cn.codeyang.web;

import cn.codeyang.core.properties.YangSecurityProperties;
import cn.codeyang.web.logout.MyLogoutSuccessHandler;
import cn.codeyang.web.session.MyExpiredSessioinStrategy;
import cn.codeyang.web.session.MyInvalidSessionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

@Configuration
public class WebSecurityBeanConfig {

    @Autowired
    private YangSecurityProperties yangSecurityProperties;

    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy(){
        return new MyInvalidSessionStrategy(yangSecurityProperties.getBrowser().getSession().getSessionInvalidUrl());
    }

    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
        return new MyExpiredSessioinStrategy(yangSecurityProperties.getBrowser().getSession().getSessionInvalidUrl());
    }
    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    public MyLogoutSuccessHandler logoutSuccessHandler(){
        return new MyLogoutSuccessHandler(yangSecurityProperties);
    }
}
