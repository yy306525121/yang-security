package cn.codeyang.core.validate.code;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {


    private static final long serialVersionUID = -6825996538152052461L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
