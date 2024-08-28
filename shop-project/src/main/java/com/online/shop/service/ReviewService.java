package com.online.shop.service;

import com.online.shop.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    Long insert(ReviewDto entityDto);

    ReviewDto update(Long id, ReviewDto entityDto);

    ReviewDto findById(Long id);

    List<ReviewDto> getAll();

    Boolean delete(Long id);
}