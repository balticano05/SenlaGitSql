package com.myioc.processors;

import com.myioc.context.ApplicationContext;
import com.myioc.annotations.Value;
import com.myioc.loaders.PropertyLoader;

import java.lang.reflect.Field;
import java.util.Properties;

import static com.myioc.utils.StringConst.ERROR_PRIVATE;
import static com.myioc.utils.StringConst.ERROR_PRIVATE_FIELD;

public class ValueProcessor implements Processor {

    private ApplicationContext applicationContext;
    private Properties properties;

    public ValueProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.properties = PropertyLoader.getProperties();
    }

    @Override
    public void process(Object bean) {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (isValueAnnotated(field)) {
                injectValue(bean, field);
            }
        }
    }

    private boolean isValueAnnotated(Field field) {
        return field.isAnnotationPresent(Value.class);
    }

    private void injectValue(Object bean, Field field) {
        String propertyValue = resolvePropertyValue(field);
        setField(bean, field, propertyValue);
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

    private void setField(Object bean, Field field, Object value) {
        try {
            field.set(bean, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(ERROR_PRIVATE_FIELD + field.getName(), e);
        }
    }

}