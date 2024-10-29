package com.online.shop.service;

import com.online.shop.dto.UserDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UserService {
    Long insert(UserDto entityDto);

    UserDto update(Long id, UserDto entityDto);

    UserDto findById(Long id);

    List<UserDto> getAll(PageRequest pageRequest);

    Boolean delete(Long id);

    UserDto findByEmail(String email);

    List<UserDto> findByDate(String date);

}