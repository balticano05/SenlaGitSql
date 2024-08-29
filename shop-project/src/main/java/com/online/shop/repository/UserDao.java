package com.online.shop.repository;

import com.online.shop.entity.Review;
import com.online.shop.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findById(Long id);

    List<Review> getAll();

    Long insert(User entity);

    Optional<User> update(Long id, User entity);

    Boolean delete(Long entity);
}