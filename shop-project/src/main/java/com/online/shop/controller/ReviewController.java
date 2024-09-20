package com.online.shop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.dto.ReviewDto;
import com.online.shop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

import static com.online.shop.Application.log;
import static com.online.shop.utils.StringConst.*;

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
            log.info(LOG_EXECUTING_INSERT_METHOD);
            ReviewDto reviewDto = objectMapper.readValue(jsonEntity, ReviewDto.class);
            return objectMapper.writeValueAsString(reviewService.insert(reviewDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String update(Long id, String jsonEntity) {
        try {
            log.info(LOG_EXECUTING_UPDATE_METHOD);
            ReviewDto reviewDto = objectMapper.readValue(jsonEntity, ReviewDto.class);
            ReviewDto result = reviewService.update(id, reviewDto);
            return objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String delete(Long id) {
        try {
            log.info(LOG_EXECUTING_DELETE_METHOD);
            return objectMapper.writeValueAsString(reviewService.delete(id));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String getAll() {
        try {
            log.info(LOG_EXECUTING_GET_ALL_METHOD);
            List<ReviewDto> reviews = reviewService.getAll();
            return objectMapper.writeValueAsString(reviews);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String getById(Long id) {
        try {
            log.info(LOG_EXECUTING_GET_BY_ID_METHOD);
            ReviewDto reviewDto = reviewService.findById(id);
            return objectMapper.writeValueAsString(reviewDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String getReviewsByUser(String email) {
        try {
            log.info(LOG_EXECUTING_GET_REVIEWS_METHOD);
            return objectMapper.writeValueAsString(reviewService.findByEmail(email));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String getByDate(String date) {
        try {
            log.info(LOG_EXECUTING_GET_BY_DATE_METHOD);
            return objectMapper.writeValueAsString(reviewService.findByDate(date));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

}