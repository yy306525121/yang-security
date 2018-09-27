package cn.codeyang.demo.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class TimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle.................................");


        // log.info(((HandlerMethod)handler).getBean().getClass().getName());
        // log.info(((HandlerMethod)handler).getMethod().getName());

        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle................................");

        Long start = (Long) request.getAttribute("startTime");
        log.info("这个接口共花了： " + (System.currentTimeMillis() - start) + "毫秒");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion........................................");

        Long start = (Long) request.getAttribute("startTime");
        log.info("这个接口共花了： " + (System.currentTimeMillis() - start) + "毫秒");
        log.info("ex is " + ex);
    }
}
