package com.myioc.processors;

import com.myioc.annotations.Autowired;
import com.myioc.resolver.DependencyResolver;

import java.lang.reflect.Field;

import static com.myioc.utils.StringConst.ERROR_BEAN_NOT_FOUND;
import static com.myioc.utils.StringConst.ERROR_PRIVATE;

public class FieldProcessor implements Processor {

    private DependencyResolver dependencyResolver;

    public FieldProcessor(DependencyResolver dependencyResolver) {
        this.dependencyResolver = dependencyResolver;
    }

    @Override
    public void process(Object bean) {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                processAutowiredField(bean, field);
            }
        }
    }

    private void processAutowiredField(Object bean, Field field) {
        Object dependency = dependencyResolver.resolveDependency(field.getType());
        if (dependency == null) {
            throw new RuntimeException(ERROR_BEAN_NOT_FOUND + field.getType().getName());
        }
        injectDependency(bean, field, dependency);
    }

    private void injectDependency(Object bean, Field field, Object dependency) {
        field.setAccessible(true);
        try {
            field.set(bean, dependency);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(ERROR_PRIVATE, e);
        }
    }

}