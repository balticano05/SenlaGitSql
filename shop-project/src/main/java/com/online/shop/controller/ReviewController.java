package com.online.shop.controller;

import com.online.shop.dto.ReviewDto;
import com.online.shop.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("secured/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public Long insert(@Valid @RequestBody ReviewDto reviewDto) {
        log.info("Executing insert method in ReviewController with JSON processing");
        return reviewService.insert(reviewDto);
    }

    @PutMapping("/{id}")
    public ReviewDto update(@PathVariable Long id, @Valid @RequestBody ReviewDto reviewDto) {
        log.info("Executing update method in ReviewController with JSON processing");
        return reviewService.update(id, reviewDto);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        log.info("Executing delete method in ReviewController with JSON processing");
        return reviewService.delete(id);
    }

    @GetMapping
    public List<ReviewDto> getAll() {
        log.info("Executing getAll method in ReviewController with JSON processing");
        return reviewService.getAll();
    }


    @GetMapping("/{id}")
    public ReviewDto getById(@PathVariable Long id) {
        log.info("Executing getById method in ReviewController with JSON processing");
        return reviewService.findById(id);
    }

    @GetMapping("/email/{email}")
    public List<ReviewDto> getReviewsByUser(@PathVariable String email) {
        log.info("Executing getReviewsByUser method in ReviewController with JSON processing");
        return reviewService.findByEmail(email);
    }

    @GetMapping("/date/{date}")
    public List<ReviewDto> getByDate(@PathVariable String date) {
        log.info("Executing getByDate method in ReviewController with JSON processing");
        return reviewService.findByDate(date);
    }

}