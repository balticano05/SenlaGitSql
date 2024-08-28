package com.online.shop.repository.impl;

import com.online.shop.entity.Role;
import com.online.shop.repository.CrudOperationsDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleDaoImpl implements CrudOperationsDao<Role> {

    private List<Role> roles;

    @Override
    public Optional<Role> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Role> getAll() {
        return List.of();
    }

    @Override
    public void insert(Role entity) {

    }

    @Override
    public void update(Long id, Role entity) {

    }

    @Override
    public void delete(Long entity) {

    }

}
