package com.online.shop.repository.impl;

import com.online.shop.repository.AbstractDao;
import com.online.shop.entity.Role;

import com.online.shop.repository.RoleDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }
}