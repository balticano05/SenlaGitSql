package com.myioc.processors;

import com.myioc.annotations.Autowired;
import com.myioc.resolver.DependencyResolver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.myioc.utils.StringConst.*;

public class SetterProcessor implements Processor {

    private DependencyResolver dependencyResolver;

    public SetterProcessor(DependencyResolver dependencyResolver) {
        this.dependencyResolver = dependencyResolver;
    }

    @Override
    public void process(Object bean) {
        Method[] methods = bean.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (isAutowired(method)) {
                injectDependency(bean, method);
            }
        }
    }

    private boolean isAutowired(Method method) {
        return method.isAnnotationPresent(Autowired.class) && method.getParameterCount() == 1;
    }

    private void injectDependency(Object bean, Method method) {
        Class<?> parameterType = method.getParameterTypes()[0];
        Object dependency = dependencyResolver.resolveDependency(parameterType);
        method.setAccessible(true);
        invokeMethod(bean, method, dependency);
    }

    private Object resolveDependency(Class<?> parameterType) {
        Object dependency = dependencyResolver.getApplicationContext().getBean(parameterType);
        if (dependency == null) {
            throw new RuntimeException(ERROR_BEAN_NOT_FOUND + parameterType.getName());
        }
        return dependency;
    }

    private void invokeMethod(Object bean, Method method, Object dependency) {
        try {
            method.invoke(bean, dependency);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(ERROR_METHOD + method.getName(), e.getTargetException());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(ERROR_PRIVATE, e);
        }
    }

}
