package com.myioc.context;

import com.myioc.loaders.PropertyLoader;
import java.util.*;

public class ApplicationContext {

    private Map<Class<?>, Object> beans = new HashMap<>();
    private Map<String, String> properties = new HashMap<>();

    public ApplicationContext(String packageName) {
        this.properties = PropertyLoader.getProperties();
        ApplicationContextService.initialize(this, packageName);
    }

    public <T> T getBean(Class<T> clazz) {
        return (T) beans.get(clazz);
    }

    void addBean(Class<?> clazz, Object bean) {
        beans.put(clazz, bean);
    }

    public String getProperty(String key) {
        return properties.get(key);
    }

}
