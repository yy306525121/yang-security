package cn.codeyang.core.social.github.api;

import org.springframework.social.ApiBinding;

public interface GitHub extends ApiBinding {
    GitHubUser getGitHubUser();
}
