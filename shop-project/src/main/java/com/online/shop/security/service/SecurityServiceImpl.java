package com.online.shop.security.service;

import com.online.shop.entity.Review;
import com.online.shop.repository.ReviewDao;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final ReviewDao reviewDao;

    public boolean isReviewOwner(Long reviewId, String currentUserEmail) {
        Review review = reviewDao.getById(reviewId).orElseThrow(() -> new EntityNotFoundException("Review not found"));
        return review.getUser().getEmail().equals(currentUserEmail);
    }

}