package com.online.shop.repository;

import com.online.shop.entity.User;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> getById(Long id);

    List<User> getAll(PageRequest pageRequest);

    Long insert(User entity);

    Optional<User> update(Long id, User entity);

    Boolean delete(Long id);

    Optional<User> findByEmail(String email);

    List<User> findByCreateDate(String createdAt);

    boolean existsByEmail(String email);
}