package com.online.shop.service.impl;

import com.online.shop.dto.RoleDto;
import com.online.shop.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

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
    public Boolean delete(Long id) {
        return null;
    }

}