package com.online.shop.service.impl;

import com.online.shop.dto.ReviewDto;
import com.online.shop.entity.Review;
import com.online.shop.repository.ReviewDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @Mock
    private ReviewDao reviewDao;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private Review review;
    private ReviewDto reviewDto;

    @BeforeEach
    void setUp() {
        review = new Review();
        review.setId(1L);
        review.setContent("test@");
        review.setRating(4);
        review.setCreatedAt(LocalDateTime.now());

        reviewDto = new ReviewDto();
        reviewDto.setId(1L);
        reviewDto.setContent("test@");
        reviewDto.setCreatedAt(LocalDateTime.now());
    }

    @Test
    void insert() {
        when(modelMapper.map(any(ReviewDto.class), eq(Review.class))).thenReturn(review);
        when(reviewDao.insert(any(Review.class))).thenReturn(1L);
        Long reviewId = reviewService.insert(reviewDto);
        assertEquals(1L, reviewId);
        verify(reviewDao, times(1)).insert(any(Review.class));
    }


    @Test
    void update() {
        doReturn(review).when(modelMapper).map(any(ReviewDto.class), eq(Review.class));
        doReturn(Optional.of(review)).when(reviewDao).update(anyLong(), any(Review.class));
        doReturn(reviewDto).when(modelMapper).map(any(Optional.class), eq(ReviewDto.class));
        ReviewDto updatedReviewDto = reviewService.update(1L, reviewDto);
        assertEquals(reviewDto.getContent(), updatedReviewDto.getContent());
        verify(reviewDao, times(1)).update(anyLong(), any(Review.class));
    }

    @Test
    void findById() {
        when(reviewDao.getById(anyLong())).thenReturn(Optional.of(review));
        when(modelMapper.map(any(Optional.class), eq(ReviewDto.class))).thenReturn(reviewDto);
        ReviewDto foundReviewDto = reviewService.findById(1L);
        assertEquals(reviewDto.getContent(), foundReviewDto.getContent());
        verify(reviewDao, times(1)).getById(anyLong());
    }

    @Test
    void getAll() {
        when(reviewDao.getAll()).thenReturn(Collections.singletonList(review));
        when(modelMapper.map(any(Review.class), eq(ReviewDto.class))).thenReturn(reviewDto);
        List<ReviewDto> reviews = reviewService.getAll();
        assertFalse(reviews.isEmpty());
        assertEquals(1, reviews.size());
        verify(reviewDao, times(1)).getAll();
    }

    @Test
    void delete() {
        when(reviewDao.delete(anyLong())).thenReturn(true);
        Boolean result = reviewService.delete(1L);
        assertTrue(result);
        verify(reviewDao, times(1)).delete(anyLong());
    }

    @Test
    void findByEmail() {
        when(reviewDao.findByEmail(anyString())).thenReturn(Collections.singletonList(review));
        when(modelMapper.map(any(Review.class), eq(ReviewDto.class))).thenReturn(reviewDto);
        List<ReviewDto> foundReviewDtos = reviewService.findByEmail("test@mail.com");
        assertFalse(foundReviewDtos.isEmpty());
        assertEquals(1, foundReviewDtos.size());
        verify(reviewDao, times(1)).findByEmail(anyString());
    }

    @Test
    void findByDate() {
        when(reviewDao.findByCreateDate(anyString())).thenReturn(Collections.singletonList(review));
        when(modelMapper.map(any(Review.class), eq(ReviewDto.class))).thenReturn(reviewDto);
        List<ReviewDto> reviews = reviewService.findByDate("2023-10-01");
        assertFalse(reviews.isEmpty());
        assertEquals(1, reviews.size());
        verify(reviewDao, times(1)).findByCreateDate(anyString());
    }

    @Test
    void insertNegative() {
        when(modelMapper.map(any(ReviewDto.class), eq(Review.class))).thenReturn(review);
        when(reviewDao.insert(any(Review.class))).thenThrow(new RuntimeException("Insert failed"));
        assertThrows(RuntimeException.class, () -> reviewService.insert(reviewDto));
        verify(reviewDao, times(1)).insert(any(Review.class));
    }

    @Test
    void updateNegative() {
        assertThrows(IllegalArgumentException.class, () -> reviewService.update(null, reviewDto));
        verify(reviewDao, never()).update(anyLong(), any(Review.class));
    }

    @Test
    void findByIdNegative() {
        assertThrows(IllegalArgumentException.class, () -> reviewService.findById(null));
        verify(reviewDao, never()).getById(anyLong());
    }

    @Test
    void getAllNegative() {
        when(reviewDao.getAll()).thenReturn(Collections.emptyList());
        List<ReviewDto> reviews = reviewService.getAll();
        assertTrue(reviews.isEmpty());
        verify(reviewDao, times(1)).getAll();
    }

    @Test
    void deleteNegative() {
        when(reviewDao.delete(anyLong())).thenReturn(false);
        Boolean result = reviewService.delete(1L);
        assertFalse(result);
        verify(reviewDao, times(1)).delete(anyLong());
    }

    @Test
    void findByEmailNegative() {
        assertThrows(IllegalArgumentException.class, () -> reviewService.findByEmail(null));
        verify(reviewDao, never()).findByEmail(anyString());
    }

    @Test
    void findByDateNegative() {
        when(reviewDao.findByCreateDate(anyString())).thenReturn(Collections.emptyList());
        List<ReviewDto> reviews = reviewService.findByDate("2023-10-01");
        assertTrue(reviews.isEmpty());
        verify(reviewDao, times(1)).findByCreateDate(anyString());
    }

}