package com.online.shop.security.service;

public interface SecurityService {
    boolean isReviewOwner(Long reviewId, String currentUserEmail);
}