package cn.codeyang.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class YangWebSecurity extends WebSecurityConfigurerAdapter {

    //@Override
    //protected void configure(HttpSecurity http) throws Exception {
    //    http.formLogin().and().csrf().disable().authorizeRequests().anyRequest().authenticated();
    //}
    //@Override
    //public void configure(HttpSecurity http) throws Exception {
    //    http.authorizeRequests().anyRequest().authenticated()
    //            .and()
    //            .requestMatchers().antMatchers("/api/**");
    //}

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
