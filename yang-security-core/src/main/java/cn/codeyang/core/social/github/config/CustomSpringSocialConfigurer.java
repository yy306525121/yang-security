package cn.codeyang.core.social.github.config;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

public class CustomSpringSocialConfigurer extends SpringSocialConfigurer {
    private String filterProcesUrl;
    private String signUpUrl;

    public CustomSpringSocialConfigurer(String filterProcesUrl, String signUpUrl) {
        this.filterProcesUrl = filterProcesUrl;
        this.signUpUrl = signUpUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcesUrl);
        //设置social的注册url
        filter.setSignupUrl(signUpUrl);
        return (T) filter;
    }



}
