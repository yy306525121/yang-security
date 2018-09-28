package cn.codeyang.app.social.login;

import cn.codeyang.core.properties.SecurityConstant;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProviderUserIdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private String providerUserIdParamter = SecurityConstant.DEFAULT_PARAMETER_NAME_PROVIDER_USER_ID;
    private String providerIdParameter = SecurityConstant.DEFAULT_PARAMETER_NAME_PROVIDER_ID;
    private boolean postOnly = true;

    public ProviderUserIdAuthenticationFilter() {
        super(new AntPathRequestMatcher(SecurityConstant.DEFAULT_LOGIN_PROCESS_URL_PROVIDER_USER_ID, HttpMethod.POST.toString()));
    }



    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (postOnly && !"POST".equalsIgnoreCase(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication not support: " + request.getMethod());
        }

        String providerUserId = obtainProviderUserId(request);
        String providerId = obtainProviderId(request);

        if (providerUserId == null) {
            providerUserId = "";
        }
        if (providerId == null) {
            providerId = "";
        }

        providerUserId = providerUserId.trim();
        providerId = providerId.trim();

        ProviderUserIdAuthenticationToken authenticationToken = new ProviderUserIdAuthenticationToken(providerUserId, providerId);
        setDetails(request, authenticationToken);

        return this.getAuthenticationManager().authenticate(authenticationToken);
    }

    private void setDetails(HttpServletRequest request, ProviderUserIdAuthenticationToken authenticationToken) {
        authenticationToken.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    private String obtainProviderId(HttpServletRequest request) {
        return request.getParameter(SecurityConstant.DEFAULT_PARAMETER_NAME_PROVIDER_ID);
    }

    private String obtainProviderUserId(HttpServletRequest request) {
        return request.getParameter(SecurityConstant.DEFAULT_PARAMETER_NAME_PROVIDER_USER_ID);
    }
}
