package com.online.shop.repository.impl;

import com.online.shop.entity.Role;
import com.online.shop.repository.GeneralDao;

import java.util.List;

public class RoleDaoImpl implements GeneralDao<Role> {

    private List<Role> roles;

    @Override
    public Role findById(Long id) {
        return null;
    }

    @Override
    public List<Role> getAll() {
        return List.of();
    }

    @Override
    public void add(Role entity) {

    }

    @Override
    public void update(Long id, Role entity) {

    }

    @Override
    public void delete(Long entity) {

    }
}
