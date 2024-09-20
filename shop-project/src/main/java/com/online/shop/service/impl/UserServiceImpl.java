package com.online.shop.service.impl;

import com.online.shop.service.UserService;
import com.online.shop.dto.UserDto;
import com.online.shop.entity.User;
import com.online.shop.repository.UserDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.online.shop.Application.log;
import static com.online.shop.utils.StringConst.*;

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
        log.info(LOG_EXECUTING_INSERT_METHOD);
        return userDao.insert(modelMapper.map(entityDto, User.class));
    }

    @Override
    public UserDto update(Long id, UserDto entityDto) {
        log.info(LOG_EXECUTING_UPDATE_METHOD);
        return modelMapper.map(userDao.update(id, modelMapper.map(entityDto, User.class)), UserDto.class);
    }

    @Override
    public UserDto findById(Long id) {
        log.info(LOG_EXECUTING_FIND_BY_ID_METHOD);
        return modelMapper.map(userDao.getById(id), UserDto.class);
    }

    @Override
    public List<UserDto> getAll() {
        log.info(LOG_EXECUTING_GET_ALL_METHOD);
        return userDao.getAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Long id) {
        log.info(LOG_EXECUTING_DELETE_METHOD);
        return userDao.delete(id);
    }

    @Override
    public UserDto findByEmail(String email) {
        log.info(LOG_EXECUTING_FIND_BY_EMAIL_METHOD);
        return modelMapper.map(userDao.findByEmail(email), UserDto.class);
    }

    @Override
    public List<UserDto> findByDate(String date) {
        log.info(LOG_EXECUTION_FIND_BY_DATE);
        return userDao.findByCreatedAt(date).stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

}