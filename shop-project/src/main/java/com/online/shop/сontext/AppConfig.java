package com.online.shop.сontext;

import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.online.shop")
@EnableJpaRepositories(basePackages = "com.online.shop.repository.impl")
@Import({MapperConfig.class, DatabaseConfig.class})
public class AppConfig {
}