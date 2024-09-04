package com.online.shop.service.impl;

import com.online.shop.annotation.Transaction;
import com.online.shop.dto.UserDto;
import com.online.shop.entity.Role;
import com.online.shop.entity.User;
import com.online.shop.repository.UserDao;
import com.online.shop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private ModelMapper modelMapper;

    public UserServiceImpl(UserDao userDao, ModelMapper modelMapper) {
        this.userDao = userDao;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transaction
    public Long insert(UserDto entityDto) {
        Role role = modelMapper.map(entityDto.getRole(), Role.class);
        User user = modelMapper.map(entityDto, User.class);
        user.setRole(role);
        return userDao.insert(user);
    }

    @Override
    @Transaction
    public UserDto update(Long id, UserDto entityDto) {
        Role role = modelMapper.map(entityDto.getRole(), Role.class);
        User user = modelMapper.map(entityDto, User.class);
        user.setRole(role);
        return modelMapper.map(userDao.update(id, user), UserDto.class);
    }

    @Override
    @Transaction
    public UserDto findById(Long id) {
        return modelMapper.map(userDao.findById(id), UserDto.class);
    }

    @Override
    @Transaction
    public List<UserDto> getAll() {
        return userDao.getAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transaction
    public Boolean delete(Long id) {
        return userDao.delete(id);
    }

}