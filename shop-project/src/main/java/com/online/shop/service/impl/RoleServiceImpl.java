package com.online.shop.service.impl;

import com.online.shop.dto.RoleDto;
import com.online.shop.service.RoleService;
import com.online.shop.service.GenericModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends GenericModelMapper implements RoleService<RoleDto> {

    public RoleServiceImpl(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public RoleDto insert(RoleDto entityDto) {
        return null;
    }

    @Override
    public RoleDto update(Long id, RoleDto entityDto) {
        return null;
    }

    @Override
    public RoleDto findById(Long id) {
        return null;
    }

    @Override
    public List<RoleDto> getAll() {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }

}
