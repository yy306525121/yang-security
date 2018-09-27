package cn.codeyang.core.social.github.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class GitHubUser implements Serializable {

    private static final long serialVersionUID = -1466817504978438659L;

    private String login;
    private Long id;
    private String avatarUrl;
}
