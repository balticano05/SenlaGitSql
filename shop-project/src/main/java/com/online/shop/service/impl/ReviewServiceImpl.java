package com.online.shop.service.impl;

import com.online.shop.service.Validator;
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
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class ReviewServiceImpl extends Validator implements ReviewService {

    private ReviewDao reviewDao;
    private ModelMapper modelMapper;

    @Autowired
    public ReviewServiceImpl(ReviewDao reviewDao, ModelMapper modelMapper) {
        this.reviewDao = reviewDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long insert(ReviewDto entityDto) {
        if (entityDto == null) {
            log.error("ReviewDto is null in insert method");
            throw new IllegalArgumentException("ReviewDto cannot be null");
        }
        log.info("Executing insert method in ReviewServiceImpl with DTO: {}", entityDto);
        return reviewDao.insert(modelMapper.map(entityDto, Review.class));
    }

    @Override
    public ReviewDto update(Long id, ReviewDto entityDto) {
        if (id == null) {
            log.error("ID is null in update method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (entityDto == null) {
            log.error("ReviewDto is null in update method");
            throw new IllegalArgumentException("ReviewDto cannot be null");
        }
        log.info("Executing update method in ReviewServiceImpl for ID: {} with DTO: {}", id, entityDto);
        return modelMapper.map(reviewDao.update(id, modelMapper.map(entityDto, Review.class)), ReviewDto.class);
    }

    @Override
    public ReviewDto findById(Long id) {
        if (id == null) {
            log.error("ID is null in findById method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        log.info("Executing findById method in ReviewServiceImpl for ID: {}", id);
        return modelMapper.map(reviewDao.getById(id), ReviewDto.class);
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
        if (id == null) {
            log.error("ID is null in delete method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        log.info("Executing delete method in ReviewServiceImpl for ID: {}", id);
        return reviewDao.delete(id);
    }

    @Override
    public List<ReviewDto> findByEmail(String email) {
        if (email == null) {
            log.error("Email is null in findByEmail method");
            throw new IllegalArgumentException("Email cannot be null");
        }
        log.info("Executing findByEmail method in ReviewServiceImpl for email: {}", email);
        return reviewDao.findByEmail(email).stream()
                .map(review -> modelMapper.map(review, ReviewDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> findByDate(String date) {
        if (date == null) {
            log.error("Date is null in findByDate method");
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (!isValidDateFormat(date)) {
            log.error("Invalid date format in findByDate method");
        }
        log.info("Executing findByDate method in ReviewServiceImpl for date: {}", date);
        return reviewDao.findByCreationDate(date).stream()
                .map(review -> modelMapper.map(review, ReviewDto.class))
                .collect(Collectors.toList());
    }

}