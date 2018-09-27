package cn.codeyang.core.validate.code.sms;

import cn.codeyang.core.properties.YangSecurityProperties;
import cn.codeyang.core.validate.code.ValidateCode;
import cn.codeyang.core.validate.code.ValidateCodeGenerator;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Data
@Component("smsValidateCodeGenerator")
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {


    @Autowired
    private YangSecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());

        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }


}
