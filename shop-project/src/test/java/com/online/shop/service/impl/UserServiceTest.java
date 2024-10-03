package com.online.shop.service.impl;

import com.online.shop.dto.UserDto;

import com.online.shop.entity.Role;
import com.online.shop.entity.User;
import com.online.shop.repository.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserDao userDao;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserDto userDto;
    private Role role;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("test@mail.com");
        user.setPassword("password@mail.com");
        user.setCreatedAt(LocalDateTime.now());

        role = new Role();
        role.setId(1L);
        user.setRole(role);

        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setEmail("test@mail.com");
        userDto.setPassword("password@mail.com");
    }

    @Test
    void insert() {
        when(modelMapper.map(any(UserDto.class), eq(User.class))).thenReturn(user);
        when(userDao.insert(any(User.class))).thenReturn(1L);
        Long userId = userService.insert(userDto);
        assertEquals(1L, userId);
        verify(userDao, times(1)).insert(any(User.class));
    }

    @Test
    void update() {
        doReturn(user).when(modelMapper).map(any(UserDto.class), eq(User.class));
        doReturn(Optional.of(user)).when(userDao).update(anyLong(), any(User.class));
        doReturn(userDto).when(modelMapper).map(any(Optional.class), eq(UserDto.class));
        UserDto updatedUserDto = userService.update(1L, userDto);
        assertEquals(userDto.getEmail(), updatedUserDto.getEmail());
        verify(userDao, times(1)).update(anyLong(), any(User.class));
    }

    @Test
    void findById() {
        when(userDao.getById(anyLong())).thenReturn(Optional.of(user));
        when(modelMapper.map(any(Optional.class), eq(UserDto.class))).thenReturn(userDto);
        UserDto foundUserDto = userService.findById(1L);
        assertEquals(userDto.getEmail(), foundUserDto.getEmail());
        verify(userDao, times(1)).getById(anyLong());
    }

    @Test
    void getAll() {
        when(userDao.getAll()).thenReturn(Collections.singletonList(user));
        when(modelMapper.map(any(User.class), eq(UserDto.class))).thenReturn(userDto);
        List<UserDto> users = userService.getAll();
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        verify(userDao, times(1)).getAll();
    }

    @Test
    void delete() {
        when(userDao.delete(anyLong())).thenReturn(true);
        Boolean result = userService.delete(1L);
        assertTrue(result);
        verify(userDao, times(1)).delete(anyLong());
    }

    @Test
    void findByEmail() {
        when(userDao.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(modelMapper.map(any(Optional.class), eq(UserDto.class))).thenReturn(userDto);
        UserDto foundUserDto = userService.findByEmail("test@mail.com");
        assertEquals(userDto.getEmail(), foundUserDto.getEmail());
        verify(userDao, times(1)).findByEmail(anyString());
    }

    @Test
    void findByDate() {
        when(userDao.findByCreateDate(anyString())).thenReturn(Collections.singletonList(user));
        when(modelMapper.map(any(User.class), eq(UserDto.class))).thenReturn(userDto);
        List<UserDto> users = userService.findByDate("2023-10-01");
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        verify(userDao, times(1)).findByCreateDate(anyString());
    }

    @Test
    void insertNegative() {
        when(modelMapper.map(any(UserDto.class), eq(User.class))).thenReturn(user);
        when(userDao.insert(any(User.class))).thenThrow(new RuntimeException("Insert failed"));
        assertThrows(RuntimeException.class, () -> userService.insert(userDto));
        verify(userDao, times(1)).insert(any(User.class));
    }

    @Test
    void updateNegative() {
        assertThrows(IllegalArgumentException.class, () -> userService.update(null, userDto));
        verify(userDao, never()).update(anyLong(), any(User.class));
    }

    @Test
    void findByIdNegative() {
        assertThrows(IllegalArgumentException.class, () -> userService.findById(null));
        verify(userDao, never()).getById(anyLong());
    }

    @Test
    void getAllNegative() {
        when(userDao.getAll()).thenReturn(Collections.emptyList());
        List<UserDto> users = userService.getAll();
        assertTrue(users.isEmpty());
        verify(userDao, times(1)).getAll();
    }

    @Test
    void deleteNegative() {
        when(userDao.delete(anyLong())).thenReturn(false);
        Boolean result = userService.delete(1L);
        assertFalse(result);
        verify(userDao, times(1)).delete(anyLong());
    }

    @Test
    void findByEmailNegative() {
        assertThrows(IllegalArgumentException.class, () -> userService.findByEmail(null));
        verify(userDao, never()).findByEmail(anyString());
    }

    @Test
    void findByDateNegative() {
        when(userDao.findByCreateDate(anyString())).thenReturn(Collections.emptyList());
        List<UserDto> users = userService.findByDate("2023-10-01");
        assertTrue(users.isEmpty());
        verify(userDao, times(1)).findByCreateDate(anyString());
    }

}