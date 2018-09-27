package cn.codeyang.core.validate.code;

import cn.codeyang.core.properties.YangSecurityProperties;
import cn.codeyang.core.validate.code.image.ImageValidateCodeGenerator;
import cn.codeyang.core.validate.code.sms.DefaultSmsCodeSender;
import cn.codeyang.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private YangSecurityProperties securityProperties;

    /**
     * 如果能在spring容器中找到imageCodeGenerator将不适用这里的初始化
     * @return
     */
    @Bean(name = "imageValidateCodeGenerator")
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageValidateCodeGenerator codeGenerator = new ImageValidateCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }
    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public DefaultSmsCodeSender smsCodeSender(){
        DefaultSmsCodeSender smsCodeSender = new DefaultSmsCodeSender();
        return smsCodeSender;
    }
}
