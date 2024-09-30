package com.online.shop.service.impl;

import com.online.shop.dto.CategoryDto;
import com.online.shop.entity.Category;
import com.online.shop.repository.CategoryDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryDao categoryDao;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category category;
    private CategoryDto categoryDto;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Test category");
        category.setDescription("Test category description");

        categoryDto = new CategoryDto();
        categoryDto.setId(1L);
        categoryDto.setName("Test category");
        categoryDto.setDescription("Test category description");
    }

    @Test
    void insert() {
        when(modelMapper.map(any(CategoryDto.class), eq(Category.class))).thenReturn(category);
        when(categoryDao.insert(any(Category.class))).thenReturn(1L);

        Long categoryId = categoryService.insert(categoryDto);

        assertEquals(1L, categoryId);
        verify(categoryDao, times(1)).insert(any(Category.class));
    }

    @Test
    void update() {
        doReturn(category).when(modelMapper).map(any(CategoryDto.class), eq(Category.class));
        doReturn(Optional.of(category)).when(categoryDao).update(anyLong(), any(Category.class));
        doReturn(categoryDto).when(modelMapper).map(any(Optional.class), eq(CategoryDto.class));

        CategoryDto updatedCategoryDto = categoryService.update(1L, categoryDto);

        assertEquals(categoryDto.getName(), updatedCategoryDto.getName());
        verify(categoryDao, times(1)).update(anyLong(), any(Category.class));
    }

    @Test
    void findById() {
        when(categoryDao.getById(anyLong())).thenReturn(Optional.of(category));
        when(modelMapper.map(any(Optional.class), eq(CategoryDto.class))).thenReturn(categoryDto);

        CategoryDto foundCategoryDto = categoryService.findById(1L);

        assertEquals(categoryDto.getName(), foundCategoryDto.getName());
        verify(categoryDao, times(1)).getById(anyLong());
    }

    @Test
    void getAll() {
        when(categoryDao.getAll()).thenReturn(Collections.singletonList(category));
        when(modelMapper.map(any(Category.class), eq(CategoryDto.class))).thenReturn(categoryDto);

        List<CategoryDto> categories = categoryService.getAll();

        assertFalse(categories.isEmpty());
        assertEquals(1, categories.size());
        verify(categoryDao, times(1)).getAll();
    }

    @Test
    void delete() {
        when(categoryDao.delete(anyLong())).thenReturn(true);

        Boolean result = categoryService.delete(1L);

        assertTrue(result);
        verify(categoryDao, times(1)).delete(anyLong());
    }

    @Test
    void insertNegative() {
        when(modelMapper.map(any(CategoryDto.class), eq(Category.class))).thenReturn(category);
        when(categoryDao.insert(any(Category.class))).thenThrow(new RuntimeException("Insert failed"));

        assertThrows(RuntimeException.class, () -> categoryService.insert(categoryDto));
        verify(categoryDao, times(1)).insert(any(Category.class));
    }

    @Test
    void updateNegative() {
        assertThrows(IllegalArgumentException.class, () -> categoryService.update(null, categoryDto));
        verify(categoryDao, never()).update(anyLong(), any(Category.class));
    }

    @Test
    void findByIdNegative() {
        assertThrows(IllegalArgumentException.class, () -> categoryService.findById(null));
        verify(categoryDao, never()).getById(anyLong());
    }

    @Test
    void getAllNegative() {
        when(categoryDao.getAll()).thenReturn(Collections.emptyList());

        List<CategoryDto> categories = categoryService.getAll();

        assertTrue(categories.isEmpty());
        verify(categoryDao, times(1)).getAll();
    }

    @Test
    void deleteNegative() {
        when(categoryDao.delete(anyLong())).thenReturn(false);

        Boolean result = categoryService.delete(1L);

        assertFalse(result);
        verify(categoryDao, times(1)).delete(anyLong());
    }
}