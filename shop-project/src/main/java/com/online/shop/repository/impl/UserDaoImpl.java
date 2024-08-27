package com.online.shop.repository.impl;

import com.online.shop.entity.User;
import com.online.shop.repository.GeneralDao;

import java.util.List;

public class UserDaoImpl implements GeneralDao<User> {

    private List<User> users;

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return List.of();
    }

    @Override
    public void add(User entity) {

    }

    @Override
    public void update(Long id, User entity) {

    }

    @Override
    public void delete(Long entity) {

    }
}
