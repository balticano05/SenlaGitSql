package com.online.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.dto.CategoryDto;
import com.online.shop.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<Long> insert(@RequestBody CategoryDto categoryDto) {
        log.info("Executing insert method in CategoryController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(categoryService.insert(categoryDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        log.info("Executing update method in CategoryController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(categoryService.update(id, categoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        log.info("Executing delete method in CategoryController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(categoryService.delete(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll() {
        log.info("Executing getAll method in CategoryController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getById(@PathVariable Long id) {
        log.info("Executing getById method in CategoryController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(categoryService.findById(id));
    }

}