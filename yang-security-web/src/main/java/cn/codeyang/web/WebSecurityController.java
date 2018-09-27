package cn.codeyang.web;

import cn.codeyang.core.properties.YangSecurityProperties;
import cn.codeyang.core.support.SimpleResponse;
import cn.codeyang.core.support.SocialUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
public class WebSecurityController {

    /**
     * request登陆请求跳转到/authentication/require之前会将请求暂存在这里
     */
    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Autowired
    private YangSecurityProperties yangSecurityProperties;
    @Autowired
    private ProviderSignInUtils providerSignInUtils;


    /**
     * 当需要身份认证时， 跳转到这里
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //取到跳转之前缓存的请求
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是 {}", targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
                redirectStrategy.sendRedirect(request, response, yangSecurityProperties.getBrowser().getLoginPage());
            }
        }

        return new SimpleResponse("访问的服务需要认证， 请引导用户跳转到登陆页面");
    }

    @GetMapping("/session/invalid")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse sessionInvalid() {
        String message = "session失效";
        return new SimpleResponse(message);
    }

    @GetMapping("/social/user")
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
        SocialUserInfo socialUserInfo = new SocialUserInfo();
        //从session中拿出本次第三方登录的信息
        Connection<?> connectionSession = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        socialUserInfo.setProviderId(connectionSession.getKey().getProviderId());
        socialUserInfo.setProviderUserId(connectionSession.getKey().getProviderUserId());
        socialUserInfo.setAvatar(connectionSession.getImageUrl());
        socialUserInfo.setNickname(connectionSession.getDisplayName());
        return socialUserInfo;
    }
}
