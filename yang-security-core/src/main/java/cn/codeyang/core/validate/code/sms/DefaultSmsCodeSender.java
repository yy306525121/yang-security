package cn.codeyang.core.validate.code.sms;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String phoneNum, String code) {
        log.info("向 {} 发送了验证码：{}", phoneNum, code);
    }
}
