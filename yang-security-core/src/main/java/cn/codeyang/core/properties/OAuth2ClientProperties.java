package cn.codeyang.core.properties;

import lombok.Data;

@Data
public class OAuth2ClientProperties {
    private String clientId;
    private String clientSecret;
    private int accessTokenValiditySeconds;
}
