package com.online.shop.service.impl;

import com.online.shop.utils.Validator;
import com.online.shop.service.ReviewService;
import com.online.shop.dto.ReviewDto;
import com.online.shop.entity.Review;
import com.online.shop.repository.ReviewDao;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDao reviewDao;
    private final ModelMapper modelMapper;

    @Override
    public Long insert(ReviewDto entityDto) {
        log.info("Executing insert method in ReviewServiceImpl with DTO: {}", entityDto);
        if (entityDto == null) {
            log.error("ReviewDto is null in insert method");
            throw new IllegalArgumentException("ReviewDto cannot be null");
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
        Optional<Review> updatedReview = Optional.ofNullable(reviewDao.update(id, modelMapper.map(entityDto, Review.class))
                .orElseThrow(() -> new EntityNotFoundException("Review not found")));
        return modelMapper.map(updatedReview, ReviewDto.class);
    }

    @Override
    public ReviewDto findById(Long id) {
        log.info("Executing findById method in ReviewServiceImpl for ID: {}", id);
        if (id == null) {
            log.error("ID is null in findById method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        Optional<Review> foundReview = Optional.ofNullable(reviewDao.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found")));
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
        return Optional.ofNullable( reviewDao.findByEmail(email))
                .filter(reviewList -> !reviewList.isEmpty())
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("List of reviews is empty in findByEmail method");
                })
                .stream()
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
        return Optional.ofNullable(reviewDao.findByCreateDate(date))
                .filter(reviewList -> !reviewList.isEmpty())
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("List of reviews is empty in findByDate method");
                })
                .stream()
                .map(review -> modelMapper.map(review, ReviewDto.class))
                .collect(Collectors.toList());
    }

}