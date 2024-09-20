package com.online.shop.сontext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.online.shop.Application.log;
import static com.online.shop.utils.StringConst.LOG_CREATING_OBJECT_MAPPER_BEAN;
import static com.online.shop.utils.StringConst.LOG_MODEL_MAPPER_BEAN;

@Configuration
public class MapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        log.info(LOG_CREATING_OBJECT_MAPPER_BEAN);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }

    @Bean
    public ModelMapper modelMapper() {
        log.info(LOG_MODEL_MAPPER_BEAN);
        return new ModelMapper();
    }

}
