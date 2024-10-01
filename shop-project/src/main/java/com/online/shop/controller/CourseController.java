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

    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody CourseDto courseDto) {
        if (courseDto.getTitle() == null || courseDto.getCoursePlan() == null
                || courseDto.getDescription() == null || courseDto.getPrice() == null) {
            throw new InvalidEntityDataException("Data are required");
        }
        try {
            log.info("Executing insert method in CourseController with JSON processing");
            Long id = courseService.insert(courseDto);
            String jsonResponse = objectMapper.writeValueAsString(id);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody CourseDto courseDto) {
        try {
            log.info("Executing update method in CourseController with JSON processing");
            CourseDto updatedCourse = courseService.update(id, courseDto);
            if (updatedCourse == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            String jsonResponse = objectMapper.writeValueAsString(updatedCourse);
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
            log.info("Executing delete method in CourseController with JSON processing");
            boolean isDeleted = courseService.delete(id);
            if (!isDeleted) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
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
            log.info("Executing getAll method in CourseController with JSON processing");
            String jsonResponse = objectMapper.writeValueAsString(courseService.getAll());
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
            log.info("Executing getById method in CourseController with JSON processing");
            String jsonResponse = objectMapper.writeValueAsString(courseService.findById(id));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<String> getByDate(@PathVariable String date) {
        try {
            log.info("Executing getByDate method in CourseController with JSON processing");
            if (!Validator.isValidDateFormat(date)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found.");
            }
            List<CourseDto> courses = courseService.findByDate(date);
            if (courses.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No courses found for the given date");
            }
            String jsonResponse = objectMapper.writeValueAsString(courses);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

}