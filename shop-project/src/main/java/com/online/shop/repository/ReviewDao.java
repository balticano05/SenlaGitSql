package com.online.shop.repository;

import com.online.shop.entity.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewDao {
    Optional<Review> getById(Long id);

    List<Review> getAll();

    Long insert(Review entity);

    Optional<Review> update(Long id, Review entity);

    Boolean delete(Long id);

    List<Review> findByEmail(String email);

    List<Review> findByCreatedAt(String createdAt);
}