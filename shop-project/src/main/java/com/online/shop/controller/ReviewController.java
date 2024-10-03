package com.online.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.dto.ReviewDto;
import com.online.shop.service.ReviewService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<Long> insert(@Valid @RequestBody ReviewDto reviewDto) {
        log.info("Executing insert method in ReviewController with JSON processing");
        return ResponseEntity.ok(reviewService.insert(reviewDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDto> update(@PathVariable Long id, @Valid @RequestBody ReviewDto reviewDto) {
        log.info("Executing update method in ReviewController with JSON processing");
        return ResponseEntity.ok(reviewService.update(id, reviewDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        log.info("Executing delete method in ReviewController with JSON processing");
        return ResponseEntity.ok(reviewService.delete(id));
    }

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAll() {
        log.info("Executing getAll method in ReviewController with JSON processing");
        return ResponseEntity.ok(reviewService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getById(@PathVariable Long id) {
        log.info("Executing getById method in ReviewController with JSON processing");
        return ResponseEntity.ok(reviewService.findById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<ReviewDto>> getReviewsByUser(@PathVariable String email) {
        log.info("Executing getReviewsByUser method in ReviewController with JSON processing");
        return ResponseEntity.ok(reviewService.findByEmail(email));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<ReviewDto>> getByDate(@PathVariable String date) {
        log.info("Executing getByDate method in ReviewController with JSON processing");
        return ResponseEntity.ok(reviewService.findByDate(date));

    }

}