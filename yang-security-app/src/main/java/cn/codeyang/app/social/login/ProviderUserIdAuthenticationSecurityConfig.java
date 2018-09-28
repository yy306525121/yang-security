package cn.codeyang.app.social.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class ProviderUserIdAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private SocialUserDetailsService userDetailsService;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        ProviderUserIdAuthenticationFilter providerUserIdAuthenticationFilter = new ProviderUserIdAuthenticationFilter();
        providerUserIdAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        providerUserIdAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        providerUserIdAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        ProviderUserIdAuthenticationProvider providerUserIdAuthenticationProvider = new ProviderUserIdAuthenticationProvider();
        providerUserIdAuthenticationProvider.setUserDetailsService(userDetailsService);
        providerUserIdAuthenticationProvider.setUsersConnectionRepository(usersConnectionRepository);

        builder.authenticationProvider(providerUserIdAuthenticationProvider).addFilterAfter(providerUserIdAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
