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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.myioc.utils.StringConst.ERROR_WITH_CREATING_INSTANCE;
import static com.myioc.utils.StringConst.ERROR_METHOD;
import static com.myioc.utils.StringConst.ERROR_NOT_FOUND_METHOD;
import static com.myioc.utils.StringConst.ERROR_PRIVATE_FIELD;

public class ApplicationContextService {

    private Reflections reflections;
    private DependencyResolver dependencyResolver;
    private Map<Class<?>, Object> beans = new HashMap<>();

    public ApplicationContextService(String packageName) {
        this.reflections = new Reflections(packageName);
        this.dependencyResolver = new DependencyResolver(beans, reflections);
    }

    public Map<Class<?>, Object> initializeBeans() {
        Set<Class<?>> components = reflections.getTypesAnnotatedWith(Component.class);
        for (Class<?> clazz : components) {
            createBean(clazz);
        }
        return beans;
    }

    private Object createBean(Class<?> clazz) {
        try {
            Object bean = instantiate(clazz);
            List<Processor> processors = List.of(
                    new ValueProcessor(beans),
                    new FieldProcessor(dependencyResolver),
                    new SetterProcessor(dependencyResolver)
            );
            for (Processor processor : processors) {
                processor.process(bean);
            }
            beans.put(clazz, bean);
            return beans;
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

    private Object instantiate(Class<?> clazz) throws InstantiationException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
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

}