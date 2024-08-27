package com.online.shop.service.impl;

import com.online.shop.service.RoleService;
import com.online.shop.service.CrudService;
import org.modelmapper.ModelMapper;

import java.util.List;

public class RoleServiceImpl extends CrudService implements RoleService {

    public RoleServiceImpl(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public Object add(Object entityDto) {
        return null;
    }

    @Override
    public Object update(Long id, Object entityDto) {
        return null;
    }

    @Override
    public Object getById(Long id) {
        return null;
    }

    @Override
    public List getAll() {
        return List.of();
    }

    @Override
    public Object delete(Long id) {
        return null;
    }
}
