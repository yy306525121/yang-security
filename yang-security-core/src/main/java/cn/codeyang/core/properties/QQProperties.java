package cn.codeyang.core.properties;

import lombok.Data;

@Data
public class QQProperties {
    private String providerId = "qq";
    private String appId;
    private String appSecret;
}
