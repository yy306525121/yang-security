package cn.codeyang.app;

import cn.codeyang.app.social.login.ProviderUserIdAuthenticationSecurityConfig;
import cn.codeyang.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import cn.codeyang.core.properties.SecurityConstant;
import cn.codeyang.core.properties.YangSecurityProperties;
import cn.codeyang.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.inject.Inject;

@Configuration
@EnableResourceServer
public class YangResourceServerConfigurer extends ResourceServerConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    @Autowired
    private ProviderUserIdAuthenticationSecurityConfig providerUserIdAuthenticationSecurityConfig;

    @Autowired
    private YangSecurityProperties yangSecurityProperties;

    @Inject
    private SpringSocialConfigurer springSocialConfigurer;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstant.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstant.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);

        http
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(validateCodeSecurityConfig)
                .and()
                .apply(providerUserIdAuthenticationSecurityConfig)
                .and()
                .apply(springSocialConfigurer)
                .and()
                .authorizeRequests()
                .antMatchers(
                        SecurityConstant.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstant.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        SecurityConstant.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                        yangSecurityProperties.getBrowser().getLoginPage(),
                        yangSecurityProperties.getBrowser().getSignUpUrl(),
                        yangSecurityProperties.getBrowser().getSignOutUrl(),
                        yangSecurityProperties.getBrowser().getSession().getSessionInvalidUrl(),
                        yangSecurityProperties.getBrowser().getSignUpUrl(),
                        yangSecurityProperties.getBrowser().getSignUpProcessUri(),
                        SecurityConstant.DEFAULT_SOCIAL_GITHUB_PROCESS_URL + "/*"
                ).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();

    }
}
