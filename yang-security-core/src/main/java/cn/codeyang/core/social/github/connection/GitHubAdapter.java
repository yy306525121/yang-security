package cn.codeyang.core.social.github.connection;

import cn.codeyang.core.social.github.api.GitHub;
import cn.codeyang.core.social.github.api.GitHubUser;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class GitHubAdapter implements ApiAdapter<GitHub> {
    @Override
    public boolean test(GitHub api) {
        return true;
    }

    @Override
    public void setConnectionValues(GitHub api, ConnectionValues values) {
        GitHubUser gitHubUser = api.getGitHubUser();
        values.setProviderUserId(String.valueOf(gitHubUser.getId()));
        values.setDisplayName(gitHubUser.getLogin());
        values.setImageUrl(gitHubUser.getAvatarUrl());
    }

    @Override
    public UserProfile fetchUserProfile(GitHub api) {
        return null;
    }

    @Override
    public void updateStatus(GitHub api, String message) {

    }
}
