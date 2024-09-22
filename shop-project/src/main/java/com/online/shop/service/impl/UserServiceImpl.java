package com.online.shop.service.impl;

import com.online.shop.service.UserService;
import com.online.shop.dto.UserDto;
import com.online.shop.entity.User;
import com.online.shop.repository.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserDao userDao, ModelMapper modelMapper) {
        this.userDao = userDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long insert(UserDto entityDto) {
        log.info("Executing insert method in UserServiceImpl with DTO: {}", entityDto);
        return userDao.insert(modelMapper.map(entityDto, User.class));
    }

    @Override
    public UserDto update(Long id, UserDto entityDto) {
        log.info("Executing update method in UserServiceImpl for ID: {} with DTO: {}", id, entityDto);
        return modelMapper.map(userDao.update(id, modelMapper.map(entityDto, User.class)), UserDto.class);
    }

    @Override
    public UserDto findById(Long id) {
        log.info("Executing findById method in UserServiceImpl for ID: {}", id);
        return modelMapper.map(userDao.getById(id), UserDto.class);
    }

    @Override
    public List<UserDto> getAll() {
        log.info("Executing getAll method in UserServiceImpl");
        return userDao.getAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Executing delete method in UserServiceImpl for ID: {}", id);
        return userDao.delete(id);
    }

    @Override
    public UserDto findByEmail(String email) {
        log.info("Executing findByEmail method in UserServiceImpl for email: {}", email);
        return modelMapper.map(userDao.findByEmail(email), UserDto.class);
    }

    @Override
    public List<UserDto> findByDate(String date) {
        log.info("Executing findByDate method in UserServiceImpl for date: {}", date);
        return userDao.findByCreatedAt(date).stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

}