package com.online.shop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.dto.CourseDto;
import com.online.shop.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import static com.online.shop.utils.StringConst.*;

@Slf4j
@Controller
public class CourseController {

    private final CourseService courseService;
    private final ObjectMapper objectMapper;

    @Autowired
    public CourseController(CourseService courseService, ObjectMapper objectMapper) {
        this.courseService = courseService;
        this.objectMapper = objectMapper;
    }

    public String insert(String jsonEntity) {
        try {
            log.info("Executing insert method in CourseController with JSON processing");
            return objectMapper.writeValueAsString(courseService.insert(objectMapper.readValue(jsonEntity, CourseDto.class)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String update(Long id, String jsonEntity) {
        try {
            log.info("Executing update method in CourseController with JSON processing");
            return objectMapper.writeValueAsString(courseService.update(id, objectMapper.readValue(jsonEntity, CourseDto.class)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String delete(Long id) {
        try {
            log.info("Executing delete method in CourseController with JSON processing");
            return objectMapper.writeValueAsString(courseService.delete(id));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String getAll() {
        try {
            log.info("Executing getAll method in CourseController with JSON processing");
            return objectMapper.writeValueAsString(courseService.getAll());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String getById(Long id) {
        try {
            log.info("Executing getById method in CourseController with JSON processing");
            return objectMapper.writeValueAsString(courseService.findById(id));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String getByDate(String date) {
        try {
            log.info("Executing getByDate method in CourseController with JSON processing");
            return objectMapper.writeValueAsString(courseService.findByDate(date));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

}