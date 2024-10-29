package com.online.shop.security.service;

public interface SecurityService {
    boolean isReviewOwner(Long reviewId, String currentUserEmail);
    boolean isUserOwner(Long userId, String currentUserEmail);
    boolean isCoursePurchasedByUser(Long courseId, Long userId);
}