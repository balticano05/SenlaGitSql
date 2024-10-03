package com.online.shop.service.impl;

import com.online.shop.exceptions.InvalidEntityDataException;
import com.online.shop.exceptions.NotFoundEntityException;
import com.online.shop.utils.Validator;
import com.online.shop.service.ReviewService;
import com.online.shop.dto.ReviewDto;
import com.online.shop.entity.Review;
import com.online.shop.repository.ReviewDao;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private ReviewDao reviewDao;
    private ModelMapper modelMapper;

    @Autowired
    public ReviewServiceImpl(ReviewDao reviewDao, ModelMapper modelMapper) {
        this.reviewDao = reviewDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long insert(ReviewDto entityDto) {
        log.info("Executing insert method in ReviewServiceImpl with DTO: {}", entityDto);
        if (entityDto == null) {
            log.error("ReviewDto is null in insert method");
            throw new IllegalArgumentException("ReviewDto cannot be null");
        }
        if (entityDto.getContent() == null || entityDto.getUser() == null
                || entityDto.getCourse() == null) {
            log.error("ReviewDto data's cannot be null insert method");
            throw new InvalidEntityDataException("Data are required");
        }
        return reviewDao.insert(modelMapper.map(entityDto, Review.class));
    }

    @Override
    public ReviewDto update(Long id, ReviewDto entityDto) {
        log.info("Executing update method in ReviewServiceImpl for ID: {} with DTO: {}", id, entityDto);
        if (id == null) {
            log.error("ID is null in update method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (entityDto == null) {
            log.error("ReviewDto is null in update method");
            throw new IllegalArgumentException("ReviewDto cannot be null");
        }
        Optional<Review> updatedReview = reviewDao.update(id, modelMapper.map(entityDto, Review.class));
        if (!updatedReview.isPresent()) {
            throw new NotFoundEntityException("Review not found");
        }
        return modelMapper.map(updatedReview, ReviewDto.class);
    }

    @Override
    public ReviewDto findById(Long id) {
        log.info("Executing findById method in ReviewServiceImpl for ID: {}", id);
        if (id == null) {
            log.error("ID is null in findById method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        Optional<Review> foundReview = reviewDao.getById(id);
        if (!foundReview.isPresent()) {
            log.error("Review not found");
            throw new NotFoundEntityException("Review not found");
        }
        return modelMapper.map(foundReview, ReviewDto.class);
    }

    @Override
    public List<ReviewDto> getAll() {
        log.info("Executing getAll method in ReviewServiceImpl");
        return reviewDao.getAll().stream()
                .map(review -> modelMapper.map(review, ReviewDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Executing delete method in ReviewServiceImpl for ID: {}", id);
        if (id == null) {
            log.error("ID is null in delete method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        return reviewDao.delete(id);
    }

    @Override
    public List<ReviewDto> findByEmail(String email) {
        log.info("Executing findByEmail method in ReviewServiceImpl for email: {}", email);
        if (email == null) {
            log.error("Email is null in findByEmail method");
            throw new IllegalArgumentException("Email cannot be null");
        }
        List<Review> foundReviews = reviewDao.findByEmail(email);
        if (foundReviews.isEmpty()) {
            log.error("Reviews not found");
            throw new NotFoundEntityException("Reviews with email " + email + " not found");
        }
        return foundReviews.stream()
                .map(review -> modelMapper.map(review, ReviewDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> findByDate(String date) {
        log.info("Executing findByDate method in ReviewServiceImpl for date: {}", date);
        if (!Validator.isValidDateFormat(date) || date == null) {
            log.error("Invalid date format in findByDate method");
            throw new IllegalArgumentException("Invalid date format in findByDate method");
        }
        List<Review> reviews = reviewDao.findByCreateDate(date);
        if (reviews.isEmpty()) {
            log.error("List of reviews is empty in findByDate method");
            throw new NotFoundEntityException("List of reviews is empty in findByDate method");
        }
        return reviews.stream()
                .map(review -> modelMapper.map(review, ReviewDto.class))
                .collect(Collectors.toList());
    }

}