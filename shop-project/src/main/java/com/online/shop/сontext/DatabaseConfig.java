package com.online.shop.сontext;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static com.online.shop.Application.log;
import static com.online.shop.utils.StringConst.*;

@Configuration
@PropertySource("classpath:application.properties")
public class DatabaseConfig {

    @Value("${driver}")
    private String driver;
    @Value("${url}")
    private String url;
    @Value("${user}")
    private String user;
    @Value("${password}")
    private String password;
    @Value("${changeLogFile}")
    private String changeLogFile;

    @Bean
    public DataSource dataSource() {
        log.info(LOG_CREATING_DATASOURCE_BEAN_WITH_URL + url);
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public SpringLiquibase liquibase() throws SQLException {
        log.info(LOG_SPRING_LIQUIBASE_BEAN_CHANGE_LOG_FILE, changeLogFile);
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource());
        liquibase.setChangeLog(changeLogFile);
        try (Connection connection = dataSource().getConnection()) {
            if (connection.isValid(2)) {
                log.info(LOG_SUCCESSFULLY_CONNECTED_TO_THE_DATABASE);
            } else {
                log.warn(LOG_FAILED_TO_VALIDATE_THE_DATABASE_CONNECTION);
            }
        } catch (SQLException e) {
            log.error(LOG_ERROR_WHILE_CHECKING_THE_DATABASE_CONNECTION, e);
        }
        return liquibase;
    }

}
