package com.online.shop.repository.impl;

import com.online.shop.entity.User;
import com.online.shop.repository.CrudOperationsDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements CrudOperationsDao<User> {

    private List<User> users;

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return List.of();
    }

    @Override
    public void insert(User entity) {

    }

    @Override
    public void update(Long id, User entity) {

    }

    @Override
    public void delete(Long entity) {

    }

}
