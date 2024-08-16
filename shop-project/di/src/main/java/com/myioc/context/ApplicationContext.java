package com.myioc.context;

import java.util.*;

public class ApplicationContext {

    private Map<Class<?>, Object> beans = new HashMap<>();

    private ApplicationContext() {
        ApplicationContextService applicationContextService = new ApplicationContextService(this);
    }

    public static ApplicationContext initializeContext() {
        return new ApplicationContext();
    }

    public <T> T getBean(Class<T> clazz) {
        return (T) beans.get(clazz);
    }

    public void addBean(Class<?> clazz, Object bean) {
        beans.put(clazz, bean);
    }

}
