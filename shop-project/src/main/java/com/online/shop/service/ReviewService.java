package com.online.shop.service;

import com.online.shop.dto.ReviewDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ReviewService {
    Long insert(ReviewDto entityDto);

    ReviewDto update(Long id, ReviewDto entityDto);

    ReviewDto findById(Long id);

    List<ReviewDto> getAll(PageRequest pageRequest);

    Boolean delete(Long id);

    List<ReviewDto> findByEmail(String email);

    List<ReviewDto> findByDate(String date);
}