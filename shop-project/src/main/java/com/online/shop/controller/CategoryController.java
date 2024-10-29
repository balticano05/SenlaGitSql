package com.online.shop.controller;

import com.online.shop.dto.CategoryDto;
import com.online.shop.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public Long insert(@Valid @RequestBody CategoryDto categoryDto) {
        log.info("Executing insert method in CategoryController with JSON processing");
        return categoryService.insert(categoryDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public CategoryDto update(@PathVariable Long id, @Valid @RequestBody CategoryDto categoryDto) {
        log.info("Executing update method in CategoryController with JSON processing");
        return categoryService.update(id, categoryDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public Boolean delete(@PathVariable Long id) {
        log.info("Executing delete method in CategoryController with JSON processing");
        return categoryService.delete(id);
    }

    @GetMapping
    public List<CategoryDto> getAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        log.info("Executing getAll method in CategoryController with JSON processing");
        return categoryService.getAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public CategoryDto getById(@PathVariable Long id) {
        log.info("Executing getById method in CategoryController with JSON processing");
        return categoryService.findById(id);
    }

}