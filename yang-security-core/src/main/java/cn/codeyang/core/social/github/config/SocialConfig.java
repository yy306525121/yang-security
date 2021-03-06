package cn.codeyang.core.social.github.config;

import cn.codeyang.core.properties.SecurityConstant;
import cn.codeyang.core.properties.YangSecurityProperties;
import cn.codeyang.core.social.SocialAuthenticationFilterPostProcessor;
import cn.codeyang.core.social.github.connection.GitHubConnectionFactory;
import cn.codeyang.core.social.github.view.YangConnectView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.web.servlet.View;

import javax.sql.DataSource;

@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {

    @Autowired
    private YangSecurityProperties yangSecurityProperties;
    @Autowired
    private DataSource dataSource;
    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;

    @Autowired(required = false)
    private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;





    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        connectionFactoryConfigurer.addConnectionFactory(
                new GitHubConnectionFactory(
                        yangSecurityProperties.getSocial().getGithub().getProviderId(),
                        yangSecurityProperties.getSocial().getGithub().getAppId(),
                        yangSecurityProperties.getSocial().getGithub().getAppSecret()
                )
        );
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository jdbcUsersConnectionRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        jdbcUsersConnectionRepository.setTablePrefix("t_");

        if (connectionSignUp != null) {
            //实现第三方登录用户和本平台用户自动绑定
            jdbcUsersConnectionRepository.setConnectionSignUp(connectionSignUp);
        }
        return jdbcUsersConnectionRepository;
    }

    /**
     * 自定义springSocial处理登陆和回调的url前缀, 默认为/auth
     * @return
     */
    @Bean
    public SpringSocialConfigurer customSpringSocialConfigurer(){
        String filterProcessUrl = SecurityConstant.DEFAULT_SOCIAL_GITHUB_PROCESS_URL;


        CustomSpringSocialConfigurer configurer = new CustomSpringSocialConfigurer(filterProcessUrl, yangSecurityProperties.getBrowser().getSignUpUrl());
        //设置social需要注册的跳转地址
        configurer.signupUrl(yangSecurityProperties.getBrowser().getSignUpUrl());
        configurer.setSocialAuthenticationFilterPostProcessor(socialAuthenticationFilterPostProcessor);
        return configurer;
    }

    /**
     * 将第三方登录的用户和本地用户关联起来的工具类
     * @param connectionFactoryLocator
     * @return
     */
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator){
        return new ProviderSignInUtils(connectionFactoryLocator, getUsersConnectionRepository(connectionFactoryLocator));
    }

    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator, connectionRepository);
    }

    @Bean({"connect/githubConnected", "connect/githubConnect"})
    @ConditionalOnMissingBean(name = "gitHubConnectedView")
    public View gitHubConnectedView(){
        return new YangConnectView();
    }



}
