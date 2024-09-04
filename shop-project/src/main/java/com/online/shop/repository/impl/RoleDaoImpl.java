package com.online.shop.repository.impl;

import com.online.shop.entity.Role;
import com.online.shop.repository.RoleDao;

import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Override
    public Role getRoleFromDatabaseById(Long roleName) {
        return new Role();
    }

}