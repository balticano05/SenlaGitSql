package com.online.shop.config;

import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "com.online.shop")
@EnableJpaRepositories(basePackages = "com.online.shop.repository.impl")
@EnableWebMvc
@Import({MapperConfig.class, DatabaseConfig.class})
public class AppConfig {
}