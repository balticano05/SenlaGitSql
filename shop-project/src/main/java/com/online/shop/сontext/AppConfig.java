package com.online.shop.сontext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.online.shop.controller.impl.ReviewControllerImpl;
import com.online.shop.repository.impl.ReviewDaoImpl;
import com.online.shop.service.impl.ReviewServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public ReviewDaoImpl reviewDao(){
        return new ReviewDaoImpl();
    }

    @Bean
    public ReviewServiceImpl reviewService(){
        return new ReviewServiceImpl(reviewDao(), modelMapper());
    }

    @Bean
    public ReviewControllerImpl reviewController(){
        return new ReviewControllerImpl(reviewService(), objectMapper());
    }

}
