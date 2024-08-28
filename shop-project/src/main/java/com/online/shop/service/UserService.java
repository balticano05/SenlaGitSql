package com.online.shop.service;

import com.online.shop.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto insert(UserDto entityDto);

    UserDto update(Long id, UserDto entityDto);

    UserDto findById(Long id);

    List<UserDto> getAll();

    Boolean delete(Long id);
}