package cn.codeyang.demo.domain;

import cn.codeyang.demo.validator.MyConstraint;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.ToString;

import javax.validation.constraints.Past;
import java.time.Instant;

//import javax.validation.constraints.NotBlank;

@ToString
public class User {

    public interface UserView{}

    public interface UserDetailView extends UserView{}

    @JsonView(UserDetailView.class)
    private Long id;

    @JsonView(UserView.class)
    @MyConstraint(message = "这是一个测试")
    private String username;

    @JsonView(UserDetailView.class)
    //@NotBlank(message = "密码不能为空")
    private String password;

    @Past
    private Instant birthday;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getBirthday() {
        return birthday;
    }

    public void setBirthday(Instant birthday) {
        this.birthday = birthday;
    }
}
