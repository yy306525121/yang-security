package cn.codeyang.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "yang.security")
public class YangSecurityProperties {
    private YangBrowserProperties browser = new YangBrowserProperties();
    private ValidateCodeProperties code = new ValidateCodeProperties();
    private SocialProperties social = new SocialProperties();
    private OAuth2Properties oauth2 = new OAuth2Properties();

}
