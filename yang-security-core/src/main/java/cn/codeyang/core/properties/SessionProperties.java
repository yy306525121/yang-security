package cn.codeyang.core.properties;

import lombok.Data;

@Data
public class SessionProperties {

    /**
     * 同一个用户在系统中最大的session数， 默认1
     */
    private int maximumSessions = 1;

    /**
     * 达到最大session时是否阻止新的登陆请求，默认为false， 不组织， 新的登陆会将老的session覆盖掉
     */
    private boolean maxSessionsPreventsLogin;

    /**
     * session失效时的跳转地址
     */
    private String sessionInvalidUrl = SecurityConstant.DEFAULT_SESSION_INVALID_URL;
}
