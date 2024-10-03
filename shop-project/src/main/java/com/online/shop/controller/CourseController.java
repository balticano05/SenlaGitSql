package com.online.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.dto.CourseDto;
import com.online.shop.service.CourseService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/courses")
public class CourseController {

    private final CourseService courseService;
    private final ObjectMapper objectMapper;

    @Autowired
    public CourseController(CourseService courseService, ObjectMapper objectMapper) {
        this.courseService = courseService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity<Long> insert(@Valid @RequestBody CourseDto courseDto) {
        log.info("Executing insert method in CourseController with JSON processing");
        return ResponseEntity.ok(courseService.insert(courseDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> update(@PathVariable Long id, @Valid @RequestBody CourseDto courseDto) {
        log.info("Executing update method in CourseController with JSON processing");
        return ResponseEntity.ok(courseService.update(id, courseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        log.info("Executing delete method in CourseController with JSON processing");
        return ResponseEntity.ok(courseService.delete(id));
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAll() {
        log.info("Executing getAll method in CourseController with JSON processing");
        return ResponseEntity.ok(courseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getById(@PathVariable Long id) {
        log.info("Executing getById method in CourseController with JSON processing");
        return ResponseEntity.ok(courseService.findById(id));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<CourseDto>> getByDate(@PathVariable String date) {
        log.info("Executing getByDate method in CourseController with JSON processing");
        return ResponseEntity.ok(courseService.findByDate(date));
    }

}