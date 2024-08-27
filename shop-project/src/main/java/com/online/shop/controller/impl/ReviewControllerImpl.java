package com.online.shop.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.controller.CrudController;
import com.online.shop.controller.ReviewController;
import com.online.shop.dto.ReviewDto;
import com.online.shop.service.impl.ReviewServiceImpl;

import java.util.List;

import static com.online.shop.utils.StringConst.EXCEPTION_PROCESSING_JSON;

public class ReviewControllerImpl extends CrudController implements ReviewController {

    private ReviewServiceImpl reviewService;

    public ReviewControllerImpl(ReviewServiceImpl reviewService, ObjectMapper objectMapper) {
        super(objectMapper);
        this.reviewService = reviewService;
    }

    @Override
    public String insert(String jsonEntity) {
        try {
            ReviewDto reviewDto = objectMapper.readValue(jsonEntity, ReviewDto.class);
            ReviewDto result = reviewService.add(reviewDto);
            return objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }

    }

    @Override
    public String update(Long id, String jsonEntity) {
        try {
            ReviewDto reviewDto = objectMapper.readValue(jsonEntity, ReviewDto.class);
            ReviewDto result = reviewService.update(id, reviewDto);
            return objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    @Override
    public String delete(Long id) {
        try {
            return objectMapper.writeValueAsString(reviewService.delete(id));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    @Override
    public String getAll() {
        try {
            List<ReviewDto> reviews = reviewService.getAll();
            return objectMapper.writeValueAsString(reviews);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    @Override
    public String getById(Long id) {
        try {
            ReviewDto reviewDto = reviewService.getById(id);
            return objectMapper.writeValueAsString(reviewDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }
}
