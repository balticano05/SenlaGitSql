package com.myioc.processors;

import com.myioc.annotations.Autowired;
import com.myioc.resolver.DependencyResolver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import static com.myioc.utils.StringConst.EXCEPTION_METHOD_INVOCATION;
import static com.myioc.utils.StringConst.EXCEPTION_PRIVATE_SETTER_ACCESS;

public class SetterProcessor implements Processor {

    private DependencyResolver dependencyResolver;

    public SetterProcessor(DependencyResolver dependencyResolver) {
        this.dependencyResolver = dependencyResolver;
    }

    @Override
    public void process(Object bean) {
        Arrays.stream(bean.getClass().getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Autowired.class) && method.getParameterCount() == 1)
                .forEach(method -> injectDependency(bean, method));
    }

    private void injectDependency(Object bean, Method method) {
        Class<?> parameterType = method.getParameterTypes()[0];
        Object dependency = dependencyResolver.resolveDependency(parameterType);
        invokeMethod(bean, method, dependency);
    }

    private void invokeMethod(Object bean, Method method, Object dependency) {
        try {
            method.setAccessible(true);
            method.invoke(bean, dependency);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(EXCEPTION_METHOD_INVOCATION + method.getName(), e.getTargetException());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(EXCEPTION_PRIVATE_SETTER_ACCESS, e);
        }
    }

}