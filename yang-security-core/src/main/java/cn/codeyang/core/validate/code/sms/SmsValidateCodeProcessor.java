package cn.codeyang.core.validate.code.sms;

import cn.codeyang.core.properties.SecurityConstant;
import cn.codeyang.core.validate.code.ValidateCode;
import cn.codeyang.core.validate.code.impl.AbstractValidateCodeProcessorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

@Component("smsValidateCodeProcessor")
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessorImpl<ValidateCode> {

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String paramName = SecurityConstant.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);

        smsCodeSender.send(mobile, validateCode.getCode());
    }
}
