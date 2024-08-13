package com.myioc.context;

import com.myioc.annotations.Autowired;
import com.myioc.annotations.Component;

import com.myioc.processors.*;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import static com.myioc.utils.StringConst.*;

public class ApplicationContextService {

    private final ApplicationContext applicationContext;
    private final List<Processor> processors = new ArrayList<>();
    private final List<Class<?>> beanDefinitions = new ArrayList<>();

    public static void initialize(ApplicationContext applicationContext, String packageName) {
        new ApplicationContextService(applicationContext, packageName);
    }

    private ApplicationContextService(ApplicationContext applicationContext, String packageName) {

        this.applicationContext = applicationContext;

        initializeProcessors();
        initializeBeanDefinitions(packageName);
        initializeBeans();
    }

    private void initializeProcessors(){
        processors.add(new ValueProcessor(applicationContext));
        processors.add(new FieldProcessor(applicationContext));
        processors.add(new SetterProcessor(applicationContext));
    }

    private void initializeBeanDefinitions(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> components = reflections.getTypesAnnotatedWith(Component.class);
        beanDefinitions.addAll(components);
    }

    private void initializeBeans() {
        for (Class<?> clazz : beanDefinitions) {
            Object bean = createBean(clazz);
            if (bean != null) {
                applicationContext.addBean(clazz, bean);
            }
        }
    }

    public Object createBean(Class<?> clazz) {
        try {

            Constructor<?> constructor = findAutowiredConstructor(clazz);
            Object[] dependencies = getDependenciesForConstructor(constructor);

            Object bean = constructor.newInstance(dependencies);

            for (Processor processor : processors) {
                processor.process(bean);
            }

            applicationContext.addBean(clazz, bean);
            return bean;

        } catch (InstantiationException e) {
            throw new RuntimeException(ERROR_WITH_CREATING_INSTANCE + clazz.getName(), e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(ERROR_METHOD + clazz.getName(), e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(ERROR_NOT_FOUND_METHOD + clazz.getName(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(ERROR_PRIVATE_FIELD + clazz.getName(), e);
        }
    }

    private Constructor<?> findAutowiredConstructor(Class<?> clazz) throws NoSuchMethodException {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        for (Constructor<?> constructor : constructors) {
            if (constructor.isAnnotationPresent(Autowired.class)) {
                return constructor;
            }
        }

        return clazz.getDeclaredConstructor();
    }

    private Object[] getDependenciesForConstructor(Constructor<?> constructor) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Object[] dependencies = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            dependencies[i] = findDependencyByType(parameterTypes[i]);
        }

        return dependencies;
    }

    private Object findDependencyByType(Class<?> parameterType) {
        Object dependency = applicationContext.getBean(parameterType);
        if (dependency == null) {
            throw new RuntimeException(ERROR_BEAN_NOT_FOUND+ parameterType.getName());
        }
        return dependency;
    }

}