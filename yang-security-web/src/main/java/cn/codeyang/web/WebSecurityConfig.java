package cn.codeyang.web;

import cn.codeyang.core.authentication.AbstractSecurityConfig;
import cn.codeyang.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import cn.codeyang.core.properties.SecurityConstant;
import cn.codeyang.core.properties.YangSecurityProperties;
import cn.codeyang.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.inject.Inject;
import javax.sql.DataSource;

/**
 * @author yangzhongyang
 */
@Configuration
public class WebSecurityConfig extends AbstractSecurityConfig {

    @Autowired
    private YangSecurityProperties yangSecurityProperties;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private DataSource dataSource;
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Inject
    private SpringSocialConfigurer springSocialConfigurer;


    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        tokenRepository.setCreateTableOnStartup(false);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        applyPasswordAuthenticationConfig(http);

        http.apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(springSocialConfigurer)
                .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(yangSecurityProperties.getBrowser().getRememberMeSecond())
                    .userDetailsService(userDetailsService)
                .and()
                .sessionManagement()
                //session验证失败后跳转的url
                .invalidSessionStrategy(invalidSessionStrategy)
                //设置最大登陆数， 当达到最大数时前一个会被踢出
                .maximumSessions(yangSecurityProperties.getBrowser().getSession().getMaximumSessions())
                //当session达到最大数时阻止当前用户登陆
                .maxSessionsPreventsLogin(yangSecurityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
                .expiredSessionStrategy(sessionInformationExpiredStrategy)
                .and()
                .and()
                .logout()
                .logoutUrl("/signOut")
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .and()
                .authorizeRequests()
                .antMatchers(
                        SecurityConstant.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstant.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        yangSecurityProperties.getBrowser().getLoginPage(),
                        SecurityConstant.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                        SecurityConstant.DEFAULT_SESSION_INVALID_URL,
                        yangSecurityProperties.getBrowser().getSignOutUrl(),
                        SecurityConstant.DEFAULT_SOCIAL_GITHUB_PROCESS_URL + "/*",
                        yangSecurityProperties.getBrowser().getSignUpUrl(),
                        yangSecurityProperties.getBrowser().getSignUpProcessUri()
                        ).permitAll()
                .antMatchers("/user/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }





}
