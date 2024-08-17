package com.myioc.resolver;

import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;

import static com.myioc.utils.StringConst.EXCEPTION_MULTIPLE_IMPLEMENTATIONS;
import static com.myioc.utils.StringConst.EXCEPTION_IMPLEMENTATION_NOT_FOUND;
import static com.myioc.utils.StringConst.EXCEPTION_CREATING_INSTANCE_FOR;

public class DependencyResolver {

    private final Map<Class<?>, Object> beans;
    private final Reflections reflections;

    public DependencyResolver(Map<Class<?>, Object> beans, Reflections reflections) {
        this.beans = beans;
        this.reflections = reflections;
    }

    public Object resolveDependency(Class<?> type) {
        if (type.isInterface()) {
            return getImplementationForInterface(type);
        }
        return beans.get(type);
    }

    private Object getImplementationForInterface(Class<?> interfaceType) {
        Set<Class<?>> implementations = reflections.getSubTypesOf((Class<Object>) interfaceType);
        if (implementations.isEmpty()) {
            throw new RuntimeException(EXCEPTION_IMPLEMENTATION_NOT_FOUND + interfaceType.getName());
        } else if (implementations.size() > 1) {
            throw new RuntimeException(EXCEPTION_MULTIPLE_IMPLEMENTATIONS + interfaceType.getName());
        }
        Class<?> implementationClass = implementations.iterator().next();
        Object implementationInstance = beans.get(implementationClass);
        if (implementationInstance == null) {
            throw new RuntimeException(EXCEPTION_CREATING_INSTANCE_FOR + implementationClass.getName());
        }
        return implementationInstance;
    }

}