package cn.codeyang.app;

import cn.codeyang.core.social.github.config.CustomSpringSocialConfigurer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 目的， spring中的所有bing在初始化之前和初始化之后都会经过这两个方法
 */
@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 在app环境下当springSocialConfigurer初始化完成之后修改signupUrl
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (StringUtils.equals(beanName, "customSpringSocialConfigurer")) {
            CustomSpringSocialConfigurer configurer = (CustomSpringSocialConfigurer) bean;
            configurer.setSignUpUrl("/social/signUp");
            return configurer;
        }
        return bean;
    }
}
