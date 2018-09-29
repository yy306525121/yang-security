package cn.codeyang.app;

import cn.codeyang.app.jwt.YangJwtTokenEnhancer;
import cn.codeyang.core.properties.YangSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.inject.Inject;

@Configuration
public class TokenStoreConfig {

    @Inject
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 匹配yang.security.oauth2 开头 .redis结尾  当他的值是redis时这个类生效
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "yang.security.oauth2", name = "storeType", havingValue = "redis")
    public TokenStore redisTokenStore(){
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setPrefix("user-token:");
        return redisTokenStore;
    }

    /**
     * 匹配yang.security.oauth2 开头 .storeType结尾  当他的值是jwt时这个类生效
     * matchIfMissing表示在配置文件中什么都不配置此配置类默认生效
     */
    @Configuration
    @ConditionalOnProperty(prefix = "yang.security.oauth2", name = "storeType", havingValue = "jwt", matchIfMissing = true)
    public static class JwtTokenConfig {
        @Autowired
        private YangSecurityProperties yangSecurityProperties;


        @Bean
        public TokenStore jwtTokenStore(){
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        /**
         * 做令牌颁发和校验作用
         * @return
         */
        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter(){
            JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
            //设置秘钥
            jwtAccessTokenConverter.setSigningKey(yangSecurityProperties.getOauth2().getJwtSigningKey());
            return jwtAccessTokenConverter;
        }

        @Bean
        @ConditionalOnMissingBean(name = "jwtTokenEnhancer")
        public TokenEnhancer jwtTokenEnhancer(){
            return new YangJwtTokenEnhancer();
        }
    }
}
