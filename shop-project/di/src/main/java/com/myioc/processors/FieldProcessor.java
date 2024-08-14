package com.myioc.processors;

import com.myioc.context.ApplicationContext;
import com.myioc.annotations.Autowired;

import java.lang.reflect.Field;

import static com.myioc.utils.StringConst.ERROR_BEAN_NOT_FOUND;
import static com.myioc.utils.StringConst.ERROR_PRIVATE;

public class FieldProcessor implements Processor {

    private final ApplicationContext applicationContext;


    public FieldProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void process(Object bean) {

        Field[] fields = bean.getClass().getDeclaredFields();

        for (Field field : fields) {

            if (isAutowired(field)) {
                processAutowiredField(bean, field);
            }
        }
    }

    private boolean isAutowired(Field field) {
        return field.isAnnotationPresent(Autowired.class);
    }

    private void processAutowiredField(Object bean, Field field) {

        Object dependency = resolveDependency(field);

        if (dependency == null) {
            throw new RuntimeException(ERROR_BEAN_NOT_FOUND + field.getType().getName());
        }

        injectDependency(bean, field, dependency);
    }

    private Object resolveDependency(Field field) {

        Class<?> fieldType = field.getType();
        return applicationContext.getBean(fieldType);
    }

    private void injectDependency(Object bean, Field field, Object dependency) {

        setFieldAccessible(field);

        try {
            field.set(bean, dependency);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(ERROR_PRIVATE, e);
        }
    }

    private void setFieldAccessible(Field field) {
        field.setAccessible(true);
    }
}