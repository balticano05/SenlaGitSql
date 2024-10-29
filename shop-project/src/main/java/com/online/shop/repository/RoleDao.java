package com.online.shop.repository;

import com.online.shop.entity.Role;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface RoleDao {
    Role getByName(String roleName);

    Optional<Role> getById(Long id);

    List<Role> getAll(PageRequest pageRequest);

    Long insert(Role entity);

    Optional<Role> update(Long id, Role entity);

    Boolean delete(Long id);
}