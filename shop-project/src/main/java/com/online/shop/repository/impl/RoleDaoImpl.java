package com.online.shop.repository.impl;

import com.online.shop.entity.Role;
import com.online.shop.repository.RoleDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleDaoImpl implements RoleDao {

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
    public Long insert(Role entity) {
        return 0L;
    }

    @Override
    public Optional<Role> update(Long id, Role entity) {
        return Optional.empty();
    }

    @Override
    public Boolean delete(Long entity) {
        return null;
    }
}