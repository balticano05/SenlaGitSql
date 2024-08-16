package com.myioc.resolver;

import com.myioc.context.ApplicationContext;
import org.reflections.Reflections;

import java.util.Set;

import static com.myioc.utils.StringConst.*;

public class DependencyResolver {

    private final ApplicationContext applicationContext;
    private Reflections reflections;

    public void setReflections(Reflections reflections) {
        this.reflections = reflections;
    }

    public DependencyResolver(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Object resolveDependency(Class<?> type) {
        if (type.isInterface()) {
            return getImplementationForInterface(type);
        }
        return applicationContext.getBean(type);
    }

    private Object getImplementationForInterface(Class<?> interfaceType) {
        Set<Class<?>> implementations = reflections.getSubTypesOf((Class<Object>) interfaceType);
        if (implementations.isEmpty()) {
            throw new RuntimeException(ERROR_IMPLEMENTATION_NOT_FOUND + interfaceType.getName());
        } else if (implementations.stream().count() > 1) {
            throw new RuntimeException(ERROR_MANY_IMPLEMENTATIONS + interfaceType.getName());
        }
        Class<?> implementationClass = implementations.iterator().next();
        try {
            return applicationContext.getBean(implementationClass);
        } catch (Exception e) {
            throw new RuntimeException(ERROR_WITH_CREATING_AN_EXAMPLE + implementationClass.getName(), e);
        }
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

}