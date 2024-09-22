package com.online.shop.repository.impl;

import com.online.shop.entity.Role;
import com.online.shop.repository.RoleDao;
import com.online.shop.сontext.AppConfig;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {AppConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
class RoleDaoImplTest {

    @Resource
    private RoleDao roleDao;

    private Role getRole() {
        Role role = new Role();
        role.setName("Test role");
        return role;
    }

    @Test
    public void create_RoleWasCreated() {
        Role role = getRole();
        Long roleId = roleDao.insert(role);
        Optional<Role> roleResult = roleDao.getById(roleId);
        assertTrue(roleResult.isPresent());
        assertEquals(role.getName(), roleResult.get().getName());
    }

    @Test
    public void findById_RoleWasFound() {
        Role role = getRole();
        Long roleId = roleDao.insert(role);
        Optional<Role> foundRole = roleDao.getById(roleId);
        assertTrue(foundRole.isPresent());
    }

    @Test
    public void delete_RoleWasDeleted() {
        Role role = getRole();
        Long roleId = roleDao.insert(role);
        roleDao.delete(roleId);
        Optional<Role> roleResult = roleDao.getById(roleId);
        assertFalse(roleResult.isPresent());
    }

    @Test
    public void update_RoleWasUpdated() {
        Role role = getRole();
        Long roleId = roleDao.insert(role);
        Optional<Role> foundRole = roleDao.getById(roleId);
        assertTrue(foundRole.isPresent());
        Role updatedRole = foundRole.get();
        updatedRole.setName("Updated role");
        roleDao.update(roleId, updatedRole);
        Optional<Role> resultRole = roleDao.getById(roleId);
        assertTrue(resultRole.isPresent());
        assertEquals(updatedRole.getName(), resultRole.get().getName());
    }

    @Test
    public void getAll_RolesWereFound() {
        List<Role> roles = roleDao.getAll();
        assertFalse(roles.isEmpty());
    }

}