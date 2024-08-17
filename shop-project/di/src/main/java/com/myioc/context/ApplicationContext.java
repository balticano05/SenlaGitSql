package com.myioc.context;

import java.util.Map;

public class ApplicationContext {

    private Map<Class<?>, Object> beans;

    private ApplicationContext(Map<Class<?>, Object> beans) {
        this.beans = beans;
    }

    public static ApplicationContext run(Class<?> startClass) {
        return new ApplicationContext(new ApplicationContextService().initializeBeans(startClass.getPackageName()));
    }

    public <T> T getBean(Class<T> clazz) {
        return (T) beans.get(clazz);
    }

}