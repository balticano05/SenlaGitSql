package com.online.shop.service.impl;

import com.online.shop.dto.ReviewDto;
import com.online.shop.entity.Review;
import com.online.shop.repository.impl.ReviewDaoImpl;
import com.online.shop.service.ReviewService;
import com.online.shop.service.CrudService;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewServiceImpl extends CrudService<ReviewDto> implements ReviewService {

    private ReviewDaoImpl reviewDao;

    public ReviewServiceImpl(ReviewDaoImpl reviewDao, ModelMapper modelMapper) {
        super(modelMapper);
        this.reviewDao = reviewDao;
    }

    @Override
    public ReviewDto add(ReviewDto entityDto) {
        Review review = modelMapper.map(entityDto, Review.class);
        reviewDao.add(review);
        return modelMapper.map(review, ReviewDto.class);
    }

    @Override
    public ReviewDto update(Long id, ReviewDto entityDto) {
        Review review = modelMapper.map(entityDto, Review.class);
        reviewDao.update(id, review);
        return modelMapper.map(review, ReviewDto.class);
    }

    @Override
    public ReviewDto getById(Long id) {
        Review review = reviewDao.findById(id);
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
    public ReviewDto delete(Long id) {
        Review review = reviewDao.findById(id);
        reviewDao.delete(id);
        return modelMapper.map(review, ReviewDto.class);
    }
}
