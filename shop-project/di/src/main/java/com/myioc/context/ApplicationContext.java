package com.myioc.context;

import java.util.Map;

public class ApplicationContext {

    private Map<Class<?>, Object> beans;

    private ApplicationContext(Map<Class<?>, Object> beans) {
        this.beans = beans;
    }

    public static ApplicationContext run(Class<?> startClass) {
        String packageName = startClass.getPackageName();
        ApplicationContextService contextService = new ApplicationContextService(packageName);
        Map<Class<?>, Object> beans = contextService.initializeBeans();
        return new ApplicationContext(beans);
    }

    public <T> T getBean(Class<T> clazz) {
        return (T) beans.get(clazz);
    }

}