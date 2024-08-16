package com.myioc.factory;

import com.myioc.annotations.Autowired;
import com.myioc.context.ApplicationContext;
import com.myioc.processors.FieldProcessor;
import com.myioc.processors.Processor;
import com.myioc.processors.SetterProcessor;
import com.myioc.processors.ValueProcessor;
import com.myioc.resolver.DependencyResolver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static com.myioc.utils.StringConst.*;
import static com.myioc.utils.StringConst.ERROR_PRIVATE_FIELD;

public class BeanFactory {

    private final ApplicationContext applicationContext;
    private final DependencyResolver dependencyResolver;
    private List<Processor> processors;

    public BeanFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.dependencyResolver = new DependencyResolver(applicationContext);
        processors = List.of(
                new ValueProcessor(applicationContext),
                new FieldProcessor(dependencyResolver),
                new SetterProcessor(dependencyResolver));
    }

    public Object createBean(Class<?> clazz) {
        try {
            Object bean = instantiate(clazz);
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

    private Object instantiate(Class<?> clazz) throws InstantiationException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Constructor<?> constructor = getAppropriateConstructor(clazz);
        Object[] dependencies = getDependenciesForConstructor(constructor);
        return constructor.newInstance(dependencies);
    }

    private Constructor<?> getAppropriateConstructor(Class<?> clazz) throws NoSuchMethodException {
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
            dependencies[i] = dependencyResolver.resolveDependency(parameterTypes[i]);
        }
        return dependencies;
    }

    public DependencyResolver getDependencyResolver() {
        return dependencyResolver;
    }

}
