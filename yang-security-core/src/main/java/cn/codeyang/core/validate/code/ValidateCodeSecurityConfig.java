package cn.codeyang.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class ValidateCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        builder.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
