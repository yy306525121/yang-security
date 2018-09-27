package cn.codeyang.demo.web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TimeAspect {
    @Around("execution(* cn.codeyang.demo.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint point) throws Throwable {
        log.info("time aspect start.......................");

        Object[] args = point.getArgs();
        for (Object arg : args) {
            log.info("arg is : " + arg);
        }

        long start = System.currentTimeMillis();
        Object proceed = point.proceed();

        log.info("这个接口共花了： " + (System.currentTimeMillis() - start) + "毫秒");

        log.info("time aspect end.......................");
        return proceed;
    }
}
