package com.online.shop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.dto.CourseDto;
import com.online.shop.exceptions.InvalidEntityDataException;
import com.online.shop.service.CourseService;
import com.online.shop.utils.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.online.shop.utils.StringConst.*;

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
    public ResponseEntity<Long> insert(@RequestBody CourseDto courseDto) {
            log.info("Executing insert method in CourseController with JSON processing");
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(courseService.insert(courseDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> update(@PathVariable Long id, @RequestBody CourseDto courseDto) {
        log.info("Executing update method in CourseController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(courseService.update(id, courseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        log.info("Executing delete method in CourseController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(courseService.delete(id));
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAll() {
        log.info("Executing getAll method in CourseController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(courseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getById(@PathVariable Long id) {
        log.info("Executing getById method in CourseController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(courseService.findById(id));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<CourseDto>> getByDate(@PathVariable String date) {
        log.info("Executing getByDate method in CourseController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(courseService.findByDate(date));
    }

}