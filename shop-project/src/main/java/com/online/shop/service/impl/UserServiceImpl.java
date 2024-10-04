package com.online.shop.service.impl;

import com.online.shop.utils.Validator;
import com.online.shop.service.UserService;
import com.online.shop.dto.UserDto;
import com.online.shop.entity.User;
import com.online.shop.repository.UserDao;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final ModelMapper modelMapper;

    @Override
    public Long insert(UserDto entityDto) {
        log.info("Executing insert method in UserServiceImpl with DTO: {}", entityDto);
        if (entityDto == null) {
            log.error("UserDto is null in insert method");
            throw new IllegalArgumentException("UserDto cannot be null");
        }
        return userDao.insert(modelMapper.map(entityDto, User.class));
    }

    @Override
    public UserDto update(Long id, UserDto entityDto) {
        log.info("Executing update method in UserServiceImpl for ID: {} with DTO: {}", id, entityDto);
        if (id == null) {
            log.error("ID is null in update method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (entityDto == null) {
            log.error("UserDto is null in update method");
            throw new IllegalArgumentException("UserDto cannot be null");
        }
        User updatedUser = userDao.update(id, modelMapper.map(entityDto, User.class))
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto findById(Long id) {
        log.info("Executing findById method in UserServiceImpl for ID: {}", id);
        if (id == null) {
            log.error("ID is null in findById method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        User foundUser = userDao.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return modelMapper.map(foundUser, UserDto.class);
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
        if (id == null) {
            log.error("ID is null in delete method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        return userDao.delete(id);
    }

    @Override
    public UserDto findByEmail(String email) {
        log.info("Executing findByEmail method in UserServiceImpl for email: {}", email);
        if (email == null) {
            log.error("Email is null in findByEmail method");
            throw new IllegalArgumentException("Email cannot be null");
        }
        User foundUser = userDao.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + email + " not found"));
        return modelMapper.map(foundUser, UserDto.class);
    }

    @Override
    public List<UserDto> findByDate(String date) {
        log.info("Executing findByDate method in UserServiceImpl for date: {}", date);
        if (date == null || !Validator.isValidDateFormat(date)) {
            log.error("Invalid date format in findByDate method");
            throw new IllegalArgumentException("Invalid date format in findByDate method");
        }
        return Optional.ofNullable(userDao.findByCreateDate(date))
                .filter(users -> !users.isEmpty())
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("List of users is empty in findByDate method");
                })
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

}