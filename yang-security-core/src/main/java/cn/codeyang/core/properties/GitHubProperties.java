package cn.codeyang.core.properties;

import lombok.Data;

@Data
public class GitHubProperties {
    private String providerId = "github";
    /**
     * Application id.
     */
    private String appId = "e0a476d575fb674565e3";

    /**
     * Application secret.
     */
    private String appSecret = "18453ceeee49003ffc29019040122b18b8072b72";

}
