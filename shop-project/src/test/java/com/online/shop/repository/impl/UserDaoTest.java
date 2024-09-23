package com.online.shop.repository.impl;

import com.online.shop.entity.Role;
import com.online.shop.entity.User;
import com.online.shop.repository.UserDao;
import com.online.shop.utils.StringConst;
import com.online.shop.сontext.AppConfig;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {AppConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
class UserDaoTest {

    @Resource
    private UserDao userDao;

    private User getUser() {
        User user = new User();
        user.setEmail("test@mail.com");
        user.setPassword("password@mail.com");
        user.setCreatedAt(LocalDateTime.now());
        Role role = new Role();
        role.setId(1L);
        user.setRole(role);
        return user;
    }

    @Test
    public void insert_UserWasInserted() {
        User user = getUser();
        Long userId = userDao.insert(user);
        Optional<User> userResult = userDao.getById(userId);
        assertTrue(userResult.isPresent());
        assertEquals(user.getEmail(), userResult.get().getEmail());
    }

    @Test
    public void findById_UserWasFound() {
        User user = getUser();
        Long userId = userDao.insert(user);
        Optional<User> foundUser = userDao.getById(userId);
        assertTrue(foundUser.isPresent());
    }

    @Test
    public void delete_UserWasDeleted() {
        User user = getUser();
        Long userId = userDao.insert(user);
        userDao.delete(userId);
        Optional<User> userResult = userDao.getById(userId);
        assertFalse(userResult.isPresent());
    }

    @Test
    public void update_UserWasUpdated() {
        User user = getUser();
        Long userId = userDao.insert(user);
        Optional<User> foundUser = userDao.getById(userId);
        assertTrue(foundUser.isPresent());
        User updatedUser = foundUser.get();
        updatedUser.setEmail("updated@mail.com");
        userDao.update(userId, updatedUser);
        Optional<User> userResult = userDao.getById(userId);
        assertTrue(userResult.isPresent());
        assertEquals(updatedUser.getEmail(), userResult.get().getEmail());
    }

    @Test
    public void getAll_UsersWereFound() {
        List<User> users = userDao.getAll();
        assertFalse(users.isEmpty());
    }

    @Test
    public void findByEmail_UserWasFound() {
        User user = getUser();
        Long userId = userDao.insert(user);
        userDao.insert(user);
        Optional<User> foundUser = userDao.findByEmail("test@mail.com");
        assertTrue(foundUser.isPresent());
        assertEquals(user.getEmail(), foundUser.get().getEmail());
    }

    @Test
    public void findByCreateDate_UsersWereFound() {
        User user = getUser();
        Long userId = userDao.insert(user);
        userDao.insert(user);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(StringConst.DATE_FORMAT);
        String createdAt = user.getCreatedAt().format(formatter);
        List<User> users = userDao.findByCreateDate(createdAt);
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        assertEquals(user.getEmail(), users.get(0).getEmail());
    }

    @Test
    public void insert_NullUser() {
        assertThrows(IllegalArgumentException.class, () -> {
            userDao.insert(null);
        });
    }

    @Test
    public void findById_NonExistentId() {
        Optional<User> userResult = userDao.getById(9999999999999L);
        assertFalse(userResult.isPresent());
    }

    @Test
    public void delete_NonExistentUser() {
        Boolean result = userDao.delete(999L);
        assertFalse(result);
    }

    @Test
    public void update_NonExistentUser() {
        User user = getUser();
        Optional<User> result = userDao.update(-1L, user);
        assertFalse(result.isPresent());
    }

    @Test
    public void findByEmail_NonExistentEmail() {
        Optional<User> userResult = userDao.findByEmail("nonexistent@mail.com");
        assertFalse(userResult.isPresent());
    }

    @Test
    public void findByCreateDate_InvalidDate() {
        assertThrows(DateTimeParseException.class, () -> {
            List<User> users = userDao.findByCreateDate("invalid-date");
        });
    }

    @Test
    public void insert_NullEmail() {
        User user = getUser();
        user.setEmail(null);
        assertThrows(PropertyValueException.class, () -> {
            userDao.insert(user);
        });
    }

    @Test
    public void insert_NullPassword() {
        User user = getUser();
        user.setPassword(null);
        assertThrows(PropertyValueException.class, () -> {
            userDao.insert(user);
        });
    }

    @Test
    public void insert_NullCreateDate() {
        User user = getUser();
        user.setCreatedAt(null);
        assertThrows(PropertyValueException.class, () -> {
            userDao.insert(user);
        });
    }

    @Test
    public void insert_NullRoleId() {
        User user = getUser();
        user.setRole(null);
        assertThrows(PropertyValueException.class, () -> {
            userDao.insert(user);
        });
    }

    @Test
    public void insert_DuplicateEmail() {
        User user1 = getUser();
        userDao.insert(user1);
        User user2 = getUser();
        assertThrows(ConstraintViolationException.class, () -> {
            userDao.insert(user2);
        });
    }

}