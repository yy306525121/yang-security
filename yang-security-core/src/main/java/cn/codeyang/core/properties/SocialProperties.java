package cn.codeyang.core.properties;

import lombok.Data;

@Data
public class SocialProperties {
    private GitHubProperties github = new GitHubProperties();
    private QQProperties qq = new QQProperties();
}
