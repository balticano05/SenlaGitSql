package com.online.shop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.dto.ReviewDto;
import com.online.shop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

import static com.online.shop.utils.StringConst.EXCEPTION_PROCESSING_JSON;

@Controller
public class ReviewController {

    private final ReviewService reviewService;
    private final ObjectMapper objectMapper;

    @Autowired
    public ReviewController(ReviewService reviewService, ObjectMapper objectMapper) {
        this.reviewService = reviewService;
        this.objectMapper = objectMapper;
    }

    public String insert(String jsonEntity) {
        try {
            ReviewDto reviewDto = objectMapper.readValue(jsonEntity, ReviewDto.class);
            return objectMapper.writeValueAsString(reviewService.insert(reviewDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String update(Long id, String jsonEntity) {
        try {
            ReviewDto reviewDto = objectMapper.readValue(jsonEntity, ReviewDto.class);
            ReviewDto result = reviewService.update(id, reviewDto);
            return objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String delete(Long id) {
        try {
            return objectMapper.writeValueAsString(reviewService.delete(id));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String getAll() {
        try {
            List<ReviewDto> reviews = reviewService.getAll();
            return objectMapper.writeValueAsString(reviews);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String getById(Long id) {
        try {
            ReviewDto reviewDto = reviewService.findById(id);
            return objectMapper.writeValueAsString(reviewDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

}