package cn.codeyang.web.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 自定义用户被提出后导致的session失效行为
 */
public class MyExpiredSessioinStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {
    public MyExpiredSessioinStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        onInvalidSession(event.getRequest(), event.getResponse());
    }

    @Override
    protected boolean isConcurrency() {
        return true;
    }
}
