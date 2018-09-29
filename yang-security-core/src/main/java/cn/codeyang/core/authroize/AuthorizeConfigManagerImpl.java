package cn.codeyang.core.authroize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * 配置管理器
 * 拿到所有的AuthorizeConfigProvider接口实现并装配
 */
@Component
public class AuthorizeConfigManagerImpl implements AuthorizeConfigManager {
    @Autowired
    private List<AuthorizeConfigProvider> authorizeConfigProviders;
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        for (AuthorizeConfigProvider authorizeConfigProvider : authorizeConfigProviders) {
            authorizeConfigProvider.config(config);
        }
//        config.anyRequest().authenticated();
    }
}
