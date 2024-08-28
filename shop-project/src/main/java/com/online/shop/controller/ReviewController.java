package com.online.shop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.dto.ReviewDto;
import com.online.shop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

import static com.online.shop.utils.StringConst.ENTITY_DELETED;
import static com.online.shop.utils.StringConst.EXCEPTION_PROCESSING_JSON;

@Controller
public class ReviewController extends GenericObjectMapper {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService, ObjectMapper objectMapper) {
        super(objectMapper);
        this.reviewService = reviewService;
    }

    public String insert(String jsonEntity) {
        try {
            ReviewDto reviewDto = objectMapper.readValue(jsonEntity, ReviewDto.class);
            Object result = reviewService.insert(reviewDto);
            return objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }

    }

    public String update(Long id, String jsonEntity) {
        try {
            ReviewDto reviewDto = objectMapper.readValue(jsonEntity, ReviewDto.class);
            Object result = reviewService.update(id, reviewDto);
            return objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String delete(Long id) {
        try {
            reviewService.delete(id);
            return objectMapper.writeValueAsString(ENTITY_DELETED);
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
            Object reviewDto = reviewService.findById(id);
            return objectMapper.writeValueAsString(reviewDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

}
