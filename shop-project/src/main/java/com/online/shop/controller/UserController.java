package com.online.shop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.dto.UserDto;
import com.online.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import static com.online.shop.Application.log;
import static com.online.shop.utils.StringConst.*;

@Controller
public class UserController {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserController(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    public String insert(String jsonEntity) {
        try {
            log.info(LOG_EXECUTING_INSERT_METHOD);
            return objectMapper.writeValueAsString(userService.insert(objectMapper.readValue(jsonEntity, UserDto.class)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String update(Long id, String jsonEntity) {
        try {
            log.info(LOG_EXECUTING_UPDATE_METHOD);
            return objectMapper.writeValueAsString(userService.update(id, objectMapper.readValue(jsonEntity, UserDto.class)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String delete(Long id) {
        try {
            log.info(LOG_EXECUTING_DELETE_METHOD);
            return objectMapper.writeValueAsString(userService.delete(id));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String getAll() {
        try {
            log.info(LOG_EXECUTING_GET_ALL_METHOD);
            return objectMapper.writeValueAsString(userService.getAll());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String getById(Long id) {
        try {
            log.info(LOG_EXECUTING_GET_BY_ID_METHOD);
            return objectMapper.writeValueAsString(userService.findById(id));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String getByEmail(String email) {
        try {
            log.info(LOG_EXECUTING_GET_BY_EMAIL_METHOD);
            return objectMapper.writeValueAsString(userService.findByEmail(email));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String getByDate(String date) {
        try {
            log.info(LOG_EXECUTING_GET_BY_DATE_METHOD);
            return objectMapper.writeValueAsString(userService.findByDate(date));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

}