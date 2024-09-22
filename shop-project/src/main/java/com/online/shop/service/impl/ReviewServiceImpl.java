package com.online.shop.service.impl;

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
        return reviewDao.insert(modelMapper.map(entityDto, Review.class));
    }

    @Override
    public ReviewDto update(Long id, ReviewDto entityDto) {
        log.info("Executing update method in ReviewServiceImpl for ID: {} with DTO: {}", id, entityDto);
        return modelMapper.map(reviewDao.update(id, modelMapper.map(entityDto, Review.class)), ReviewDto.class);
    }

    @Override
    public ReviewDto findById(Long id) {
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
        log.info("Executing delete method in ReviewServiceImpl for ID: {}", id);
        return reviewDao.delete(id);
    }

    @Override
    public List<ReviewDto> findByEmail(String email) {
        log.info("Executing findByEmail method in ReviewServiceImpl for email: {}", email);
        return reviewDao.findByEmail(email).stream()
                .map(review -> modelMapper.map(review, ReviewDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> findByDate(String date) {
        log.info("Executing findByDate method in ReviewServiceImpl for date: {}", date);
        return reviewDao.findByCreationDate(date).stream()
                .map(review -> modelMapper.map(review, ReviewDto.class))
                .collect(Collectors.toList());
    }

}