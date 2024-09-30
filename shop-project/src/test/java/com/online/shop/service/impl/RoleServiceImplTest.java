package com.online.shop.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.online.shop.dto.RoleDto;
import com.online.shop.entity.Role;
import com.online.shop.repository.RoleDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    @Mock
    private RoleDao roleDao;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private RoleServiceImpl roleService;

    private Role role;
    private RoleDto roleDto;

    @BeforeEach
    void setUp() {
        role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");
        roleDto = new RoleDto();
        roleDto.setId(1L);
        roleDto.setName("ROLE_USER");
    }

    @Test
    void insert() {
        when(modelMapper.map(any(RoleDto.class), eq(Role.class))).thenReturn(role);
        when(roleDao.insert(any(Role.class))).thenReturn(1L);
        Long roleId = roleService.insert(roleDto);
        assertEquals(1L, roleId);
        verify(roleDao, times(1)).insert(any(Role.class));
    }

    @Test
    void update() {
        when(modelMapper.map(any(RoleDto.class), eq(Role.class))).thenReturn(role);
        when(roleDao.update(anyLong(), any(Role.class))).thenReturn(Optional.of(role));
        when(modelMapper.map(any(Optional.class), eq(RoleDto.class))).thenReturn(roleDto);
        RoleDto updatedRoleDto = roleService.update(1L, roleDto);
        assertEquals(roleDto.getName(), updatedRoleDto.getName());
        verify(roleDao, times(1)).update(anyLong(), any(Role.class));
    }

    @Test
    void findById() {
        when(roleDao.getById(anyLong())).thenReturn(Optional.of(role));
        when(modelMapper.map(any(Optional.class), eq(RoleDto.class))).thenReturn(roleDto);
        RoleDto foundRoleDto = roleService.findById(1L);
        assertEquals(roleDto.getName(), foundRoleDto.getName());
        verify(roleDao, times(1)).getById(anyLong());
    }

    @Test
    void getAll() {
        when(roleDao.getAll()).thenReturn(Collections.singletonList(role));
        when(modelMapper.map(any(Role.class), eq(RoleDto.class))).thenReturn(roleDto);
        List<RoleDto> roles = roleService.getAll();
        assertFalse(roles.isEmpty());
        assertEquals(1, roles.size());
        verify(roleDao, times(1)).getAll();
    }

    @Test
    void delete() {
        when(roleDao.delete(anyLong())).thenReturn(true);
        Boolean result = roleService.delete(1L);
        assertTrue(result);
        verify(roleDao, times(1)).delete(anyLong());
    }

    @Test
    void insertNegative() {
        when(modelMapper.map(any(RoleDto.class), eq(Role.class))).thenReturn(role);
        when(roleDao.insert(any(Role.class))).thenThrow(new RuntimeException("Insert failed"));
        assertThrows(RuntimeException.class, () -> roleService.insert(roleDto));
        verify(roleDao, times(1)).insert(any(Role.class));
    }

    @Test
    void updateNegative() {
        assertThrows(IllegalArgumentException.class, () -> roleService.update(null, roleDto));
        verify(roleDao, never()).update(anyLong(), any(Role.class));
    }

    @Test
    void findByIdNegative() {
        assertThrows(IllegalArgumentException.class, () -> roleService.findById(null));
        verify(roleDao, never()).getById(anyLong());
    }

    @Test
    void getAllNegative() {
        when(roleDao.getAll()).thenReturn(Collections.emptyList());
        List<RoleDto> roles = roleService.getAll();
        assertTrue(roles.isEmpty());
        verify(roleDao, times(1)).getAll();
    }

    @Test
    void deleteNegative() {
        when(roleDao.delete(anyLong())).thenReturn(false);
        Boolean result = roleService.delete(1L);
        assertFalse(result);
        verify(roleDao, times(1)).delete(anyLong());
    }

}