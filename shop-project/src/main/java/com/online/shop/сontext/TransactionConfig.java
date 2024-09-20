package com.online.shop.сontext;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

import java.util.Properties;

import static com.online.shop.Application.log;
import static com.online.shop.utils.StringConst.LOG_CREATING_JPA_TRANSACTION_MANAGER_BEAN;
import static com.online.shop.utils.StringConst.LOG_CREATING_LOCAL_CONTAINER_MANAGER_BEAN;

@Configuration
@EnableTransactionManagement
public class TransactionConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        log.info(LOG_CREATING_LOCAL_CONTAINER_MANAGER_BEAN);
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setPackagesToScan("com.online.shop");
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", "none");
        factoryBean.setJpaProperties(jpaProperties);
        return factoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        log.info(LOG_CREATING_JPA_TRANSACTION_MANAGER_BEAN);
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
