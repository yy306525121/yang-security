package cn.codeyang.demo.web.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

// @Component
@Slf4j
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("filter init............");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        log.info("filter start.............");
        long start = System.currentTimeMillis();
        filterChain.doFilter(request, response);
        log.info("这个接口共花了： " + (System.currentTimeMillis() - start) + "毫秒");
        log.info("filter finish.............");
    }

    @Override
    public void destroy() {
        log.info("filter destroy...........");
    }
}
