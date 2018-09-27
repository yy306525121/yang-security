//package cn.codeyang.web;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
///**
// * @author yangzhongyang
// */
//@Component
//@Slf4j
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        log.info("登陆用户名 {}", username);
//        return new User("yangzhongyang", passwordEncoder.encode("hello"), true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,user"));
//    }
//}
