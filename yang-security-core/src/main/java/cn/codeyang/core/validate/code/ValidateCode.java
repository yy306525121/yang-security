package cn.codeyang.core.validate.code;

import java.io.Serializable;
import java.time.LocalDateTime;


public class ValidateCode implements Serializable {


    private static final long serialVersionUID = -687494543970603302L;

    public ValidateCode(String code, int expireInSecond) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireInSecond);
    }
    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    private String code;
    private LocalDateTime expireTime;

    public boolean isExpried(){
        return LocalDateTime.now().compareTo(expireTime) > 0;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
