package com.online.shop.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.online.shop")
@EnableJpaRepositories(basePackages = "com.online.shop.repository.impl")
@EnableWebMvc
@EnableTransactionManagement
@Import({MapperConfig.class, DatabaseConfig.class, SecurityConfig.class})
public class AppConfig {
}