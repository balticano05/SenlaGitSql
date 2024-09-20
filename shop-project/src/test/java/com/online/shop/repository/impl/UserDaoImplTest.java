package com.online.shop.repository.impl;

import com.online.shop.entity.Role;
import com.online.shop.entity.User;
import com.online.shop.repository.UserDao;
import com.online.shop.utils.StringConst;
import com.online.shop.utils.TestConsts;
import com.online.shop.сontext.AppConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {AppConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
class UserDaoImplTest {

    @Resource
    private UserDao userDao;

    private User createTestUser() {
        User user = new User();
        user.setEmail(TestConsts.USER_TEST_EMAIL);
        user.setPassword(TestConsts.USER_TEST_PASSWORD);
        user.setCreatedAt(LocalDateTime.now());
        Role role = new Role();
        role.setId(1L);
        user.setRole(role);
        return user;
    }

    @Test
    public void insert_UserWasInserted() {
        User user = createTestUser();
        Long userId = userDao.insert(user);
        Optional<User> userResult = userDao.getById(userId);
        assertTrue(userResult .isPresent());
        assertEquals(user.getEmail(), userResult .get().getEmail());
    }

    @Test
    public void findById_UserWasFound() {
        User user = createTestUser();
        Long userId = userDao.insert(user);
        Optional<User> foundUser = userDao.getById(userId);
        assertTrue(foundUser.isPresent());
    }

    @Test
    public void delete_UserWasDeleted() {
        User user = createTestUser();
        Long userId = userDao.insert(user);
        userDao.delete(userId);
        Optional<User> userResult = userDao.getById(userId);
        assertFalse(userResult.isPresent());
    }

    @Test
    public void update_UserWasUpdated() {
        User user = createTestUser();
        Long userId = userDao.insert(user);
        Optional<User> foundUser = userDao.getById(userId);
        assertTrue(foundUser.isPresent());
        User updatedUser = foundUser.get();
        updatedUser.setEmail(TestConsts.USER_TEST_UPDATED_EMAIL);
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
        User user = createTestUser();
        Long userId = userDao.insert(user);
        userDao.insert(user);
        Optional<User> foundUser = userDao.findByEmail(TestConsts.USER_TEST_EMAIL);
        assertTrue(foundUser.isPresent());
        assertEquals(user.getEmail(), foundUser.get().getEmail());
    }

    @Test
    public void findByCreatedAt_UsersWereFound() {
        User user = createTestUser();
        Long userId = userDao.insert(user);
        userDao.insert(user);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(StringConst.DATE_FORMAT);
        String createdAt = user.getCreatedAt().format(formatter);
        List<User> users = userDao.findByCreatedAt(createdAt);
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        assertEquals(user.getEmail(), users.get(0).getEmail());
    }

}