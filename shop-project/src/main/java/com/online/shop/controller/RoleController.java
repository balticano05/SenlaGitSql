package com.online.shop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.dto.RoleDto;
import com.online.shop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import static com.online.shop.Application.log;
import static com.online.shop.utils.StringConst.*;

@Controller
public class RoleController {

    private final RoleService roleService;
    private final ObjectMapper objectMapper;

    @Autowired
    public RoleController(RoleService roleService, ObjectMapper objectMapper) {
        this.roleService = roleService;
        this.objectMapper = objectMapper;
    }

    public String insert(String jsonEntity) {
        try {
            log.info(LOG_EXECUTING_INSERT_METHOD);
            return objectMapper.writeValueAsString(roleService.insert(objectMapper.readValue(jsonEntity, RoleDto.class)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String update(Long id, String jsonEntity) {
        try {
            log.info(LOG_EXECUTING_UPDATE_METHOD);
            return objectMapper.writeValueAsString(roleService.update(id, objectMapper.readValue(jsonEntity, RoleDto.class)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String delete(Long id) {
        try {
            log.info(LOG_EXECUTING_DELETE_METHOD);
            return objectMapper.writeValueAsString(roleService.delete(id));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String getAll() {
        try {
            log.info(LOG_EXECUTING_GET_ALL_METHOD);
            return objectMapper.writeValueAsString(roleService.getAll());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String getById(Long id) {
        try {
            log.info(LOG_EXECUTING_GET_BY_ID_METHOD);
            return objectMapper.writeValueAsString(roleService.findById(id));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

}