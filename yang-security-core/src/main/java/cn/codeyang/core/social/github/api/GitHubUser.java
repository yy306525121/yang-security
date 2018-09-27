package cn.codeyang.core.social.github.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class GitHubUser implements Serializable {

    private static final long serialVersionUID = 6158640234340779820L;

    private long id;

    private String url;

    private String login;

    private String avatarUrl;

    private String gravatarId;

}
