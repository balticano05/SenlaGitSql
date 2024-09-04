package com.online.shop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.dto.UserDto;
import com.online.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

import static com.online.shop.utils.StringConst.EXCEPTION_PROCESSING_JSON;

@Controller
public class UserController {

    private UserService userService;
    private ObjectMapper objectMapper;

    @Autowired
    public UserController(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    public String insert(String jsonEntity) {
        try {
            UserRequest userRequest = objectMapper.readValue(jsonEntity, UserRequest.class);
            UserDto userDto = userRequest.getUser();
            return objectMapper.writeValueAsString(userService.insert(userDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String update(Long id, String jsonEntity) {
        try {
            UserRequest userRequest = objectMapper.readValue(jsonEntity, UserRequest.class);
            UserDto userDto = userRequest.getUser();
            UserDto result = (UserDto) userService.update(id, userDto);
            return objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String delete(Long id) {
        try {
            return objectMapper.writeValueAsString(userService.delete(id));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String getAll() {
        try {
            List<UserDto> users = userService.getAll();
            return objectMapper.writeValueAsString(users);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String getById(Long id) {
        try {
            UserDto userDto = (UserDto) userService.findById(id);
            return objectMapper.writeValueAsString(userDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

}