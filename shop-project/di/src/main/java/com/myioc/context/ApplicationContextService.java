package com.myioc.context;

import com.myioc.annotations.Autowired;
import com.myioc.annotations.Component;

import com.myioc.processors.FieldProcessor;
import com.myioc.processors.Processor;
import com.myioc.processors.SetterProcessor;
import com.myioc.processors.ValueProcessor;
import com.myioc.resolver.DependencyResolver;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.myioc.utils.StringConst.EXCEPTION_CREATING_INSTANCE;
import static com.myioc.utils.StringConst.EXCEPTION_METHOD_INVOCATION;
import static com.myioc.utils.StringConst.EXCEPTION_METHOD_NOT_FOUND;
import static com.myioc.utils.StringConst.EXCEPTION_PRIVATE_FIELD_ACCESS;

public class ApplicationContextService {

    public Map<Class<?>, Object> initializeBeans(String packageName) {
        Map<Class<?>, Object> beans = new HashMap<>();
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> components = reflections.getTypesAnnotatedWith(Component.class);
        DependencyResolver dependencyResolver = new DependencyResolver(beans, reflections);
        for (Class<?> clazz : components) {
            Object bean = initializeBean(clazz, dependencyResolver);
            beans.put(clazz, bean);
        }
        return beans;
    }

    private Object initializeBean(Class<?> clazz, DependencyResolver dependencyResolver) {
        try {
            Object bean = instantiate(clazz, dependencyResolver);
            List<Processor> processors = List.of(
                    new ValueProcessor(),
                    new FieldProcessor(dependencyResolver),
                    new SetterProcessor(dependencyResolver)
            );
            for (Processor processor : processors) {
                processor.process(bean);
            }
            return bean;
        } catch (InstantiationException e) {
            throw new RuntimeException(EXCEPTION_CREATING_INSTANCE + clazz.getName(), e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(EXCEPTION_METHOD_INVOCATION + clazz.getName(), e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(EXCEPTION_METHOD_NOT_FOUND + clazz.getName(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(EXCEPTION_PRIVATE_FIELD_ACCESS + clazz.getName(), e);
        }
    }

    private Object instantiate(Class<?> clazz, DependencyResolver dependencyResolver) throws InstantiationException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Constructor<?> constructor = getAppropriateConstructor(clazz);
        Object[] dependencies = getDependenciesForConstructor(constructor, dependencyResolver);
        return constructor.newInstance(dependencies);
    }

    private Constructor<?> getAppropriateConstructor(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredConstructors())
                .filter(constructor -> constructor.isAnnotationPresent(Autowired.class))
                .findFirst()
                .orElseGet(() -> {
                    try {
                        return clazz.getDeclaredConstructor();
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException(EXCEPTION_METHOD_NOT_FOUND + clazz.getName(), e);
                    }
                });
    }

    private Object[] getDependenciesForConstructor(Constructor<?> constructor, DependencyResolver dependencyResolver) {
        return Arrays.stream(constructor.getParameterTypes())
                .map(dependencyResolver::resolveDependency)
                .toArray();
    }

}