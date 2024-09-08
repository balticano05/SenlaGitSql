package com.online.shop.service.impl;

import com.online.shop.dto.ReviewDto;
import com.online.shop.entity.Review;
import com.online.shop.repository.ReviewDao;
import com.online.shop.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewDao reviewDao;
    private ModelMapper modelMapper;

    @Autowired
    public ReviewServiceImpl(ReviewDao reviewDao, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.reviewDao = reviewDao;
    }

    @Override
    public Long insert(ReviewDto entityDto) {
        Review review = modelMapper.map(entityDto, Review.class);
        return reviewDao.insert(review);
    }

    @Override
    public ReviewDto update(Long id, ReviewDto entityDto) {
        Review review = modelMapper.map(entityDto, Review.class);
        return modelMapper.map(reviewDao.update(id, review), ReviewDto.class);
    }

    @Override
    public ReviewDto findById(Long id) {
        Object review = reviewDao.getById(id);
        return modelMapper.map(review, ReviewDto.class);
    }

    @Override
    public List<ReviewDto> getAll() {
        List<Review> reviews = reviewDao.getAll();
        return reviews.stream()
                .map(review -> modelMapper.map(review, ReviewDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Long id) {
        return reviewDao.delete(id);
    }

}
