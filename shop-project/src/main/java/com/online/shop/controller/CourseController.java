package com.online.shop.controller;

import com.online.shop.dto.CourseDto;
import com.online.shop.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public Long insert(@Valid @RequestBody CourseDto courseDto) {
        log.info("Executing insert method in CourseController with JSON processing");
        return courseService.insert(courseDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public CourseDto update(@PathVariable Long id, @Valid @RequestBody CourseDto courseDto) {
        log.info("Executing update method in CourseController with JSON processing");
        return courseService.update(id, courseDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public Boolean delete(@PathVariable Long id) {
        log.info("Executing delete method in CourseController with JSON processing");
        return courseService.delete(id);
    }

    @GetMapping("/front")
    public List<CourseDto> getAll() {
        log.info("Executing getAll method in CourseController with JSON processing");
        return courseService.getAll();
    }

    @GetMapping("/front/{id}")
    public CourseDto getById(@PathVariable Long id) {
        log.info("Executing getById method in CourseController with JSON processing");
        return courseService.findById(id);
    }

    @GetMapping("/front/date/{date}")
    public List<CourseDto> getByDate(@PathVariable String date) {
        log.info("Executing getByDate method in CourseController with JSON processing");
        return courseService.findByDate(date);
    }

}