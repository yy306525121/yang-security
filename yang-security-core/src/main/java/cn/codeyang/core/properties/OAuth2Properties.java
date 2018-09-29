package cn.codeyang.core.properties;

import lombok.Data;

@Data
public class OAuth2Properties {
    private OAuth2ClientProperties[] clients = {};
    private String jwtSigningKey = "yang";
}
