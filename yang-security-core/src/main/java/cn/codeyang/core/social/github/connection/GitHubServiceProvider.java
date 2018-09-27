package cn.codeyang.core.social.github.connection;

import cn.codeyang.core.social.github.api.GitHub;
import cn.codeyang.core.social.github.api.impl.GitHubTemplate;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

public class GitHubServiceProvider extends AbstractOAuth2ServiceProvider<GitHub> {

    public static final String URL_AUTHORIZE = "https://github.com/login/oauth/authorize";
    public static final String URL_ACCESS_TOKEN = "https://github.com/login/oauth/access_token";


    public GitHubServiceProvider(String clientId, String clientSecret) {
        super(createOAuth2Template(clientId, clientSecret));
    }

    private static OAuth2Template createOAuth2Template(String clientId, String clientSecret) {
        GitHubOAuth2Template oAuth2Template = new GitHubOAuth2Template(clientId, clientSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN);
        return oAuth2Template;
    }

    @Override
    public GitHub getApi(String accessToken) {
        return new GitHubTemplate(accessToken);
    }
}
