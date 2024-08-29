package com.online.shop.repository.impl;

import com.online.shop.entity.Review;
import com.online.shop.entity.User;
import com.online.shop.repository.UserDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private List<User> users;

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Review> getAll() {
        return List.of();
    }

    @Override
    public Long insert(User entity) {
        return 0L;
    }

    @Override
    public Optional<User> update(Long id, User entity) {
        return Optional.empty();
    }

    @Override
    public Boolean delete(Long entity) {
        return null;
    }

}