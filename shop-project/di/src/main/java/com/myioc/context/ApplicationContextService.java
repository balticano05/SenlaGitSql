package com.myioc.context;

import com.myioc.annotations.Component;

import com.myioc.factory.BeanFactory;
import org.reflections.Reflections;

import java.util.Set;


import static com.myioc.utils.StringConst.*;

public class ApplicationContextService {

    private final ApplicationContext applicationContext;
    private final BeanFactory beanFactory;

    public ApplicationContextService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.beanFactory = new BeanFactory(applicationContext);
        initializeBeans();
    }

    private void initializeBeans() {
        Reflections reflections = new Reflections(PACKAGE_NAME);
        beanFactory.getDependencyResolver().setReflections(reflections);
        Set<Class<?>> components = reflections.getTypesAnnotatedWith(Component.class);
        for (Class<?> clazz : components) {
            beanFactory.createBean(clazz);
        }
    }

}