package com.online.shop.сontext;

import liquibase.configuration.LiquibaseConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.online.shop")
@EnableJpaRepositories(basePackages = "com.online.shop.repository.impl")
@Import({LiquibaseConfiguration.class, DatabaseConfig.class, TransactionConfig.class})
public class AppConfig {
}