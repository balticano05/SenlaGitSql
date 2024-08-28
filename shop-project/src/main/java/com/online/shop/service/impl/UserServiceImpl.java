package com.online.shop.service.impl;

import com.online.shop.dto.UserDto;
import com.online.shop.service.GenericModelMapper;
import com.online.shop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends GenericModelMapper implements UserService<UserDto> {

    public UserServiceImpl(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public UserDto insert(UserDto entityDto) {
        return null;
    }

    @Override
    public UserDto update(Long id, UserDto entityDto) {
        return null;
    }

    @Override
    public UserDto findById(Long id) {
        return null;
    }

    @Override
    public List<UserDto> getAll() {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }

}
