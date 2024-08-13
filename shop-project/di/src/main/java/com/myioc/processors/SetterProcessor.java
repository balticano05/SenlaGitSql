package com.myioc.processors;

import com.myioc.context.ApplicationContext;
import com.myioc.annotations.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.myioc.utils.StringConst.*;

public class SetterProcessor implements Processor {

    private ApplicationContext applicationContext;

    public SetterProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
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
        Object dependency = resolveDependency(parameterType);

        setMethodAccessible(method);
        invokeMethod(bean, method, dependency);
    }

    private Object resolveDependency(Class<?> parameterType) {
        Object dependency = applicationContext.getBean(parameterType);
        if (dependency == null) {
            throw new RuntimeException(ERROR_BEAN_NOT_FOUND + parameterType.getName());
        }
        return dependency;
    }

    private void setMethodAccessible(Method method) {
        method.setAccessible(true);
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
