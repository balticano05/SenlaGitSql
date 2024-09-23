package com.online.shop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.dto.CategoryDto;
import com.online.shop.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import static com.online.shop.utils.StringConst.*;

@Slf4j
@Controller
public class CategoryController {

    private final CategoryService categoryService;
    private final ObjectMapper objectMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, ObjectMapper objectMapper) {
        this.categoryService = categoryService;
        this.objectMapper = objectMapper;
    }

    public String insert(String jsonEntity) {
        try {
            log.info("Executing insert method in CategoryController with JSON processing");
            return objectMapper.writeValueAsString(categoryService.insert(objectMapper.readValue(jsonEntity, CategoryDto.class)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String update(Long id, String jsonEntity) {
        try {
            log.info("Executing update method in CategoryController with JSON processing");
            return objectMapper.writeValueAsString(categoryService.update(id, objectMapper.readValue(jsonEntity, CategoryDto.class)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String delete(Long id) {
        try {
            log.info("Executing delete method in CategoryController with JSON processing");
            return objectMapper.writeValueAsString(categoryService.delete(id));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String getAll() {
        try {
            log.info("Executing getAll method in CategoryController with JSON processing");
            return objectMapper.writeValueAsString(categoryService.getAll());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String getById(Long id) {
        try {
            log.info("Executing getById method in CategoryController with JSON processing");
            return objectMapper.writeValueAsString(categoryService.findById(id));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

}