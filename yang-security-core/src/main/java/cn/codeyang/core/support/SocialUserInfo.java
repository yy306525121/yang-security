package cn.codeyang.core.support;

import lombok.Data;

import java.io.Serializable;

@Data
public class SocialUserInfo implements Serializable {

    private static final long serialVersionUID = -3124562240866700885L;

    private String providerId;
    private String providerUserId;
    private String nickname;
    private String avatar;
}
