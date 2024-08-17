package com.myioc.processors;

import com.myioc.annotations.Value;
import com.myioc.loaders.PropertyLoader;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

import static com.myioc.utils.StringConst.EXCEPTION_PRIVATE_FIELD_ACCESS;

public class ValueProcessor implements Processor {

    private Map<Class<?>, Object> beans;

    public ValueProcessor(Map<Class<?>, Object> beans) {
        this.beans = beans;
    }

    @Override
    public void process(Object bean) {
        Arrays.stream(bean.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Value.class))
                .forEach(field -> injectValue(bean, field));
    }

    private void injectValue(Object bean, Field field) {
        try {
            String propertyValue = resolvePropertyValue(field);
            field.setAccessible(true);
            field.set(bean, propertyValue);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(EXCEPTION_PRIVATE_FIELD_ACCESS + field.getName(), e);
        }
    }

    private String resolvePropertyValue(Field field) {
        Value valueAnnotation = field.getAnnotation(Value.class);
        String propertyKey = valueAnnotation.value();
        return PropertyLoader.getProperty(propertyKey);
    }

}