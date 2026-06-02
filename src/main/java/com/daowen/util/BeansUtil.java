package com.daowen.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BeansUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        BeansUtil.applicationContext = context;
    }

    public static void setStaticApplicationContext(ApplicationContext context) {
        BeansUtil.applicationContext = context;
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> Map<String, T> getBeanOfType(Class<T> type) {
        return applicationContext.getBeansOfType(type);
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}