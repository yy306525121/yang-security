package cn.codeyang.app;

import cn.codeyang.core.properties.OAuth2ClientProperties;
import cn.codeyang.core.properties.YangSecurityProperties;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class YangAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    @Qualifier("demoUserDetailsService")
    private UserDetailsService userDetailsService;
    @Autowired
    private YangSecurityProperties yangSecurityProperties;
    @Autowired
    private TokenStore redisTokenStore;
    //
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();

        if (ArrayUtils.isNotEmpty(yangSecurityProperties.getOauth2().getClients())) {
            for (OAuth2ClientProperties client : yangSecurityProperties.getOauth2().getClients()) {
                builder
                        .withClient(client.getClientId())
                        .secret(passwordEncoder.encode(client.getClientSecret()))
                        .accessTokenValiditySeconds(client.getAccessTokenValiditySeconds())
                        .authorizedGrantTypes("authorization_code", "password", "refresh_token")
                        .scopes("all", "read", "write");
            }
        }

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenStore(redisTokenStore);
    }

//@Override
    //public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    //    security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    //}
}
