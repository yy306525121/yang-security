package cn.codeyang.demo.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DemoUserDetailsService implements UserDetailsService, SocialUserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("表单登陆用户名 {}", username);
        if ("yangzhongyang".equals(username)) {
            return new User("yangzhongyang", passwordEncoder.encode("hello"), true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN,ROLE_USER"));
        } else if ("yy306525121".equals(username)){
            return new User("yy306525121", passwordEncoder.encode("hello"), true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
        }
        return null;
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        log.info("社交登陆用户名userId: {}", userId);
        if ("yangzhongyang".equals(userId)) {
            return new SocialUser("yangzhongyang", passwordEncoder.encode("hello"), true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN,ROLE_USER"));
        } else if ("yy306525121".equals(userId)){
            return new SocialUser("yy306525121", passwordEncoder.encode("hello"), true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
        }
        return null;
    }
}
