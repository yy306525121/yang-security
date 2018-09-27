package cn.codeyang.core.validate.code.sms;

public interface SmsCodeSender {
    void send(String phoneNum, String code);
}
