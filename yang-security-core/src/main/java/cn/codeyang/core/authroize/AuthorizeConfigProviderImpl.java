package cn.codeyang.core.authroize;

import cn.codeyang.core.properties.SecurityConstant;
import cn.codeyang.core.properties.YangSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

@Component
public class AuthorizeConfigProviderImpl implements AuthorizeConfigProvider {

    @Autowired
    private YangSecurityProperties yangSecurityProperties;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(
                SecurityConstant.DEFAULT_UNAUTHENTICATION_URL,
                SecurityConstant.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                yangSecurityProperties.getBrowser().getLoginPage(),
                SecurityConstant.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                SecurityConstant.DEFAULT_SESSION_INVALID_URL,
                yangSecurityProperties.getBrowser().getSignOutUrl(),
                SecurityConstant.DEFAULT_SOCIAL_GITHUB_PROCESS_URL + "/*",
                yangSecurityProperties.getBrowser().getSignUpUrl(),
                yangSecurityProperties.getBrowser().getSignUpProcessUri()
        ).permitAll();
    }
}
