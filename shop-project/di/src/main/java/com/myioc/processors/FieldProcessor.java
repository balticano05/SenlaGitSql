package com.myioc.processors;

import com.myioc.annotations.Autowired;
import com.myioc.resolver.DependencyResolver;

import java.lang.reflect.Field;
import java.util.Arrays;

import static com.myioc.utils.StringConst.EXCEPTION_BEAN_NOT_FOUND;
import static com.myioc.utils.StringConst.EXCEPTION_PRIVATE_FIELD_ACCESS;

public class FieldProcessor implements Processor {

    private DependencyResolver dependencyResolver;

    public FieldProcessor(DependencyResolver dependencyResolver) {
        this.dependencyResolver = dependencyResolver;
    }

    @Override
    public void process(Object bean) {
        Arrays.stream(bean.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Autowired.class))
                .forEach(field -> processAutowiredField(bean, field));
    }

    private void processAutowiredField(Object bean, Field field) {
        Object dependency = dependencyResolver.resolveDependency(field.getType());
        if (dependency == null) {
            throw new RuntimeException(EXCEPTION_BEAN_NOT_FOUND + field.getType().getName());
        }
        injectDependency(bean, field, dependency);
    }

    private void injectDependency(Object bean, Field field, Object dependency) {
        try {
            field.setAccessible(true);
            field.set(bean, dependency);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(EXCEPTION_PRIVATE_FIELD_ACCESS, e);
        }
    }

}