package cn.codeyang.demo.web.controller;

//import cn.codeyang.app.social.AppSignUpUtils;
import cn.codeyang.core.properties.YangSecurityProperties;
import cn.codeyang.demo.domain.User;
import cn.codeyang.demo.domain.UserQueryCondition;
import cn.codeyang.demo.exception.UserNotExistException;
import com.fasterxml.jackson.annotation.JsonView;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;
//    @Autowired
//    private AppSignUpUtils appSignUpUtils;
    @Autowired
    private YangSecurityProperties yangSecurityProperties;

    //@GetMapping("/me")
    //public Object getCurrentUser(Authentication authentication){
    //    //return SecurityContextHolder.getContext().getAuthentication();
    //    return authentication;
    //}

    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails user){
        return user;
    }

    /**
     * 使用jwt之后session中存放的不再是UserDetails
     *
     * @param user
     * @return
     */
//    @GetMapping("/me")
//    public Object getCurrentUser(Authentication user, HttpServletRequest request) throws UnsupportedEncodingException {
//
//        String header = request.getHeader("Authorization");
//        String token = StringUtils.substringAfter(header, "bearer ");
//        Claims claims = Jwts.parser().setSigningKey(yangSecurityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8")).parseClaimsJws(token).getBody();
//        String company = (String) claims.get("company");
//        log.info("company: {}", company);
//        return user;
//    }

    @GetMapping()
    @JsonView(value = User.UserDetailView.class)
    public List<User> query(UserQueryCondition condition) {
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        User user = new User();
        user.setUsername("aaa");
        user.setPassword("hello");

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user);
        userList.add(user);
        return userList;
    }


    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
        }

        System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));

        user.setId(1L);
        return user;

    }

    @PutMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User update(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> {
                FieldError fieldError = (FieldError) error;
                String message = fieldError.getField() + ": " + error.getDefaultMessage();
                System.out.println(message);
            });
        }

        System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));

        user.setId(1L);
        return user;
    }


    @DeleteMapping("/{id:\\d+}")
    public void delete(@ApiParam("用户ID") @PathVariable Long id) {
        System.out.println(id);
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable Long id) {
        throw new UserNotExistException(id);
    }

    @PostMapping("/register")
    public void register(User user, HttpServletRequest request) {
        //TODO 注册用户
        log.info("User: {}", user.toString());
        String userId = user.getUsername();
        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
//        appSignUpUtils.doPostSignUp(new ServletWebRequest(request), userId);
    }
}
