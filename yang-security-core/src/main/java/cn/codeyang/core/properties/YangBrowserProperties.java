package cn.codeyang.core.properties;

import lombok.Data;

@Data
public class YangBrowserProperties {
    private String loginPage = "/signIn.html";
    private String signUpUrl = "/register.html";
    private String signUpProcessUri = "/user/register";

    private String signOutUrl = "/signOut.html";

    private LoginType loginType = LoginType.JSON;

    private int rememberMeSecond = 3600;

    private SessionProperties session = new SessionProperties();
}
