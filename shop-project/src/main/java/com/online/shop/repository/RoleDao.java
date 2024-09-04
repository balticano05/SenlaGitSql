package com.online.shop.repository;

import com.online.shop.entity.Role;

public interface RoleDao {
    Role getRoleFromDatabaseById(Long roleId);
}