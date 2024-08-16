package com.myioc.processors;

import com.myioc.annotations.Value;
import com.myioc.loaders.PropertyLoader;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Properties;

import static com.myioc.utils.StringConst.ERROR_PRIVATE;
import static com.myioc.utils.StringConst.ERROR_PRIVATE_FIELD;

public class ValueProcessor implements Processor {

    private Map<Class<?>, Object> beans;
    private Properties properties;

    public ValueProcessor(Map<Class<?>, Object> beans) {
        this.beans = beans;
        this.properties = PropertyLoader.getProperties();
    }

    @Override
    public void process(Object bean) {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Value.class)) {
                injectValue(bean, field);
            }
        }
    }

    private void injectValue(Object bean, Field field) {
        String propertyValue = resolvePropertyValue(field);
        try {
            field.set(bean, propertyValue);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(ERROR_PRIVATE_FIELD + field.getName(), e);
        }
    }

    private String resolvePropertyValue(Field field) {
        try {
            field.setAccessible(true);
            Value valueAnnotation = field.getAnnotation(Value.class);
            String propertyKey = valueAnnotation.value();
            return properties.getProperty(propertyKey);
        } catch (Exception e) {
            throw new RuntimeException(ERROR_PRIVATE + field.getName(), e);
        }
    }

}