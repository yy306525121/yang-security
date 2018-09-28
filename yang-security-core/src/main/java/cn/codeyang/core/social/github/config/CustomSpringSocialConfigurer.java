package cn.codeyang.core.social.github.config;

import cn.codeyang.core.social.SocialAuthenticationFilterPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.stereotype.Component;

public class CustomSpringSocialConfigurer extends SpringSocialConfigurer {
    private String filterProcesUrl;
    private String signUpUrl;
    private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

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

        if (socialAuthenticationFilterPostProcessor != null) {
            socialAuthenticationFilterPostProcessor.process(filter);
        }

        return (T) filter;
    }


    public SocialAuthenticationFilterPostProcessor getSocialAuthenticationFilterPostProcessor() {
        return socialAuthenticationFilterPostProcessor;
    }

    public void setSocialAuthenticationFilterPostProcessor(SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor) {
        this.socialAuthenticationFilterPostProcessor = socialAuthenticationFilterPostProcessor;
    }

}
