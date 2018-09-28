package cn.codeyang.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * 当使用授权码换取令牌时
 * 处理浏览器和app的不同返回效果
 */
public interface SocialAuthenticationFilterPostProcessor {
    void process(SocialAuthenticationFilter socialAuthenticationFilter);
}
