package com.online.shop.repository.impl;

import com.online.shop.entity.Course;
import com.online.shop.entity.Review;
import com.online.shop.entity.User;
import com.online.shop.repository.ReviewDao;
import com.online.shop.utils.StringConst;
import com.online.shop.сontext.AppConfig;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {AppConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
class ReviewDaoTest {

    @Resource
    private ReviewDao reviewDao;

    private Review getReview() {
        Review review = new Review();
        review.setContent("Test review content");
        review.setCreatedAt(LocalDateTime.now());
        review.setRating(4);
        review.setCourse(Course.builder().id(4L).build());
        review.setUser(User.builder().id(4L).email("alice.smith@gmail.com").build());
        return review;
    }

    @Test
    void getById_ReviewWasFound() {
        Review review = getReview();
        Long reviewId = reviewDao.insert(review);
        Optional<Review> resultReview = reviewDao.getById(reviewId);
        assertTrue(resultReview.isPresent());
        assertEquals(review.getContent(), resultReview.get().getContent());
    }

    @Test
    void getAll_ReviewsWasFound() {
        List<Review> reviews = reviewDao.getAll();
        assertFalse(reviews.isEmpty());
    }

    @Test
    void insert_ReviewWasInserted() {
        Review review = getReview();
        Long reviewId = reviewDao.insert(review);
        Optional<Review> resultReview = reviewDao.getById(reviewId);
        assertTrue(resultReview.isPresent());
        assertEquals(review.getContent(), resultReview.get().getContent());
    }

    @Test
    void update_ReviewWasUpdate() {
        Review review = getReview();
        Long reviewId = reviewDao.insert(review);
        Optional<Review> resultReview = reviewDao.getById(reviewId);
        assertTrue(resultReview.isPresent());
        Review updatedReview = resultReview.get();
        updatedReview.setContent("Updated content");
        reviewDao.update(reviewId, updatedReview);
        Optional<Review> reviewResult = reviewDao.getById(reviewId);
        assertTrue(reviewResult.isPresent());
        assertEquals(updatedReview.getContent(), reviewResult.get().getContent());
    }

    @Test
    void delete_ReviewWasDeleted() {
        Review review = getReview();
        Long reviewId = reviewDao.insert(review);
        reviewDao.delete(reviewId);
        Optional<Review> reviewResult = reviewDao.getById(reviewId);
        assertFalse(reviewResult.isPresent());
    }

    @Test
    void findByEmail_ReviewWasFound() {
        Review review = getReview();
        reviewDao.insert(review);
        List<Review> reviews = reviewDao.findByEmail("alice.smith@gmail.com");
        assertFalse(reviews.isEmpty());
        boolean contentMatches = false;
        for (Review r : reviews) {
            if (r.getContent().equals("Test review content")) {
                contentMatches = true;
                break;
            }
        }
        assertTrue(contentMatches);
    }

    @Test
    void findByCreationDate_ReviewWasFound() {
        Review review = getReview();
        reviewDao.insert(review);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(StringConst.DATE_FORMAT);
        String createdAt = review.getCreatedAt().format(formatter);
        List<Review> reviews = reviewDao.findByCreationDate(createdAt);
        assertFalse(reviews.isEmpty());
        assertEquals(1, reviews.size());
        assertEquals(review, reviews.get(0));
    }

    @Test
    void getById_NonExistentId() {
        Optional<Review> reviewResult = reviewDao.getById(9999999999999L);
        assertFalse(reviewResult.isPresent());
    }

    @Test
    void insert_NullReview() {
        assertThrows(IllegalArgumentException.class, () -> {
            reviewDao.insert(null);
        });
    }

    @Test
    void update_NonExistentReview() {
        Review review = getReview();
        Optional<Review> result = reviewDao.update(999L, review);
        assertFalse(result.isPresent());
    }

    @Test
    void delete_NonExistentReview() {
        Boolean result = reviewDao.delete(999L);
        assertFalse(result);
    }

    @Test
    void findByEmail_NonExistentEmail() {
        List<Review> reviews = reviewDao.findByEmail("nonexistent@mail.com");
        assertTrue(reviews.isEmpty());
    }

    @Test
    void findByCreationDate_InvalidDate() {
        assertThrows(DateTimeParseException.class, () -> {
            List<Review> reviews = reviewDao.findByCreationDate("invalid-date");
        });
    }

}