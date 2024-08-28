package com.online.shop.service.impl;

import com.online.shop.dto.ReviewDto;
import com.online.shop.entity.Review;
import com.online.shop.repository.ReviewDao;
import com.online.shop.service.ReviewService;
import com.online.shop.service.GenericModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl extends GenericModelMapper implements ReviewService<ReviewDto> {

    private final ReviewDao reviewDao;

    @Autowired
    public ReviewServiceImpl(ReviewDao reviewDao, ModelMapper modelMapper) {
        super(modelMapper);
        this.reviewDao = reviewDao;
    }

    @Override
    public ReviewDto insert(ReviewDto entityDto) {
        Review review = modelMapper.map(entityDto, Review.class);
        reviewDao.insert(review);
        return modelMapper.map(review, ReviewDto.class);
    }

    @Override
    public ReviewDto update(Long id, ReviewDto entityDto) {
        Review review = modelMapper.map(entityDto, Review.class);
        reviewDao.update(id, review);
        return modelMapper.map(reviewDao.findById(id), ReviewDto.class);
    }

    @Override
    public ReviewDto findById(Long id) {
        Object review = reviewDao.findById(id);
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
    public void delete(Long id) {
        reviewDao.delete(id);
    }

}
