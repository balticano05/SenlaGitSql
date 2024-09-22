package com.online.shop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.dto.CoursePlanDto;
import com.online.shop.service.CoursePlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import static com.online.shop.utils.StringConst.*;

@Slf4j
@Controller
public class CoursePlanController {

    private final CoursePlanService coursePlanService;
    private final ObjectMapper objectMapper;

    @Autowired
    public CoursePlanController(CoursePlanService coursePlanService, ObjectMapper objectMapper) {
        this.coursePlanService = coursePlanService;
        this.objectMapper = objectMapper;
    }

    public String insert(String jsonEntity) {
        try {
            log.info("Executing insert method in CoursePlanController with JSON processing");
            return objectMapper.writeValueAsString(coursePlanService.insert(objectMapper.readValue(jsonEntity, CoursePlanDto.class)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String update(Long id, String jsonEntity) {
        try {
            log.info("Executing update method in CoursePlanController with JSON processing");
            return objectMapper.writeValueAsString(coursePlanService.update(id, objectMapper.readValue(jsonEntity, CoursePlanDto.class)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String delete(Long id) {
        try {
            log.info("Executing delete method in CoursePlanController with JSON processing");
            return objectMapper.writeValueAsString(coursePlanService.delete(id));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String getAll() {
        try {
            log.info("Executing getAll method in CoursePlanController with JSON processing");
            return objectMapper.writeValueAsString(coursePlanService.getAll());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String getById(Long id) {
        try {
            log.info("Executing getById method in CoursePlanController with JSON processing");
            return objectMapper.writeValueAsString(coursePlanService.findById(id));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

}