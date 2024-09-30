package com.online.shop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.dto.CategoryDto;
import com.online.shop.exceptions.InvalidEntityDataException;
import com.online.shop.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.online.shop.utils.StringConst.*;

@Slf4j
@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final ObjectMapper objectMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, ObjectMapper objectMapper) {
        this.categoryService = categoryService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody CategoryDto categoryDto) {
        if (categoryDto.getName() == null || categoryDto.getDescription() == null) {
            throw new InvalidEntityDataException("Data are required");
        }
        try {
            log.info("Executing insert method in CategoryController with JSON processing");
            Long id = categoryService.insert(categoryDto);
            String jsonResponse = objectMapper.writeValueAsString(id);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        try {
            log.info("Executing update method in CategoryController with JSON processing");
            CategoryDto updatedCategory = categoryService.update(id, categoryDto);
            if (updatedCategory == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            String jsonResponse = objectMapper.writeValueAsString(updatedCategory);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            log.info("Executing delete method in CategoryController with JSON processing");
            boolean isDeleted = categoryService.delete(id);
            if (!isDeleted) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
            }
            String jsonResponse = objectMapper.writeValueAsString(isDeleted);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<String> getAll() {
        try {
            log.info("Executing getAll method in CategoryController with JSON processing");
            String jsonResponse = objectMapper.writeValueAsString(categoryService.getAll());
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<String> getById(@PathVariable Long id) {
        try {
            log.info("Executing getById method in CategoryController with JSON processing");
            String jsonResponse = objectMapper.writeValueAsString(categoryService.findById(id));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

}