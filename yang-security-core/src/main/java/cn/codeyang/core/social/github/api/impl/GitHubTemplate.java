package cn.codeyang.core.social.github.api.impl;

import cn.codeyang.core.social.github.api.GitHub;
import cn.codeyang.core.social.github.api.GitHubUser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

@Slf4j
public class GitHubTemplate extends AbstractOAuth2ApiBinding implements GitHub{

    public final String URL_GET_USER_INFO = "https://api.github.com/user?access_token=%s";

    private ObjectMapper objectMapper = new ObjectMapper();

    private String accessToken;

    public GitHubTemplate(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.accessToken = accessToken;
    }


    @Override
    public GitHubUser getGitHubUser() {
        String url = String.format(URL_GET_USER_INFO, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        log.info("url: {}   result: {}", url, result);


        try {
            JSONObject jsonObject = JSON.parseObject(result);
            GitHubUser gitHubUser = new GitHubUser();
            gitHubUser.setId(jsonObject.getLong("id"));
            gitHubUser.setUrl(jsonObject.getString("html_url"));
            gitHubUser.setLogin(jsonObject.getString("login"));
            gitHubUser.setAvatarUrl(jsonObject.getString("avatar_url"));
            gitHubUser.setGravatarId(jsonObject.getString("gravatar_id"));
            return gitHubUser;
        } catch (Exception e) {
            throw new RuntimeException("获取用户信息失败", e);
        }
    }
}
