package com.online.shop.service.impl;

import com.online.shop.entity.User;
import com.online.shop.service.CrudService;
import com.online.shop.service.UserService;
import org.modelmapper.ModelMapper;

import java.util.List;

public class UserServiceImpl extends CrudService<User> implements UserService {

    public UserServiceImpl(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public User add(User entityDto) {
        return null;
    }

    @Override
    public User update(Long id, User entityDto) {
        return null;
    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return List.of();
    }

    @Override
    public User delete(Long id) {
        return null;
    }
}
