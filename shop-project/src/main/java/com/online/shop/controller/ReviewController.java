package com.online.shop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.dto.ReviewDto;
import com.online.shop.dto.RoleDto;
import com.online.shop.entity.Review;
import com.online.shop.exceptions.InvalidEntityDataException;
import com.online.shop.service.ReviewService;
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
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final ObjectMapper objectMapper;

    @Autowired
    public ReviewController(ReviewService reviewService, ObjectMapper objectMapper) {
        this.reviewService = reviewService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody ReviewDto reviewDto) {
        if (reviewDto.getContent() == null || reviewDto.getUser() == null
                || reviewDto.getCourse() == null) {
            throw new InvalidEntityDataException("Data are required");
        }
        try {
            log.info("Executing insert method in ReviewController with JSON processing");
            Long id = reviewService.insert(reviewDto);
            String jsonResponse = objectMapper.writeValueAsString(id);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody ReviewDto reviewDto) {
        try {
            log.info("Executing update method in ReviewController with JSON processing");
            ReviewDto updatedReview = reviewService.update(id, reviewDto);
            if (updatedReview == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            String jsonResponse = objectMapper.writeValueAsString(updatedReview);
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
            log.info("Executing delete method in ReviewController with JSON processing");
            boolean isDeleted = reviewService.delete(id);
            if (!isDeleted) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found");
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
            log.info("Executing getAll method in ReviewController with JSON processing");
            String jsonResponse = objectMapper.writeValueAsString(reviewService.getAll());
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
            log.info("Executing getById method in ReviewController with JSON processing");
            String jsonResponse = objectMapper.writeValueAsString(reviewService.findById(id));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<String> getReviewsByUser(@PathVariable String email) {
        try {
            log.info("Executing getReviewsByUser method in ReviewController with JSON processing");
            List<ReviewDto> reviews = reviewService.findByEmail(email);
            if (reviews.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found");
            }
            String jsonResponse = objectMapper.writeValueAsString(reviews);
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
            log.info("Executing getByDate method in ReviewController with JSON processing");
            if(!Validator.isValidDateFormat(date)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad format of date.");
            }
            List<ReviewDto> reviews = reviewService.findByDate(date);
            if (reviews.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reviews not found");
            }
            String jsonResponse = objectMapper.writeValueAsString(reviews);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

}