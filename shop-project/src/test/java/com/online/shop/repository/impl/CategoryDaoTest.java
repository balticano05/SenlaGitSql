package com.online.shop.repository.impl;

import com.online.shop.entity.Category;
import com.online.shop.repository.CategoryDao;
import com.online.shop.config.AppConfig;
import jakarta.annotation.Resource;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = AppConfig.class)
@WebAppConfiguration
@Transactional
class CategoryDaoTest {

    @Resource
    private CategoryDao categoryDao;

    private Category getCategory() {
        Category category = new Category();
        category.setName("Test category");
        category.setDescription("Test category description");
        return category;
    }

    @Test
    public void insert_CategoryWasInserted() {
        Category category = getCategory();
        Long categoryId = categoryDao.insert(category);
        Optional<Category> categoryResult = categoryDao.getById(categoryId);
        assertTrue(categoryResult.isPresent());
        assertEquals(category.getName(), categoryResult.get().getName());
    }

    @Test
    public void getById_CategoryWasFound() {
        Category category = getCategory();
        Long categoryId = categoryDao.insert(category);
        Optional<Category> foundCategory = categoryDao.getById(categoryId);
        assertTrue(foundCategory.isPresent());
    }

    @Test
    public void delete_CategoryWasDeleted() {
        Category category = getCategory();
        Long categoryId = categoryDao.insert(category);
        categoryDao.delete(categoryId);
        Optional<Category> categoryResult = categoryDao.getById(categoryId);
        assertFalse(categoryResult.isPresent());
    }

    @Test
    public void update_CategoryWasUpdated() {
        Category category = getCategory();
        Long categoryId = categoryDao.insert(category);
        Optional<Category> foundCategory = categoryDao.getById(categoryId);
        assertTrue(foundCategory.isPresent());
        Category updatedCategory = foundCategory.get();
        updatedCategory.setName("Updated category");
        categoryDao.update(categoryId, updatedCategory);
        Optional<Category> categoryResult = categoryDao.getById(categoryId);
        assertTrue(categoryResult.isPresent());
        assertEquals(updatedCategory.getName(), categoryResult.get().getName());
    }

    @Test
    public void getAll_CategoriesWereFound() {
        List<Category> categories = categoryDao.getAll();
        assertFalse(categories.isEmpty());
    }

    @Test
    public void insert_NullCategory() {
        assertThrows(IllegalArgumentException.class, () -> {
            categoryDao.insert(null);
        });
    }

    @Test
    public void getById_NonExistentId() {
        Optional<Category> categoryResult = categoryDao.getById(9999999999999L);
        assertFalse(categoryResult.isPresent());
    }

    @Test
    public void delete_NonExistentCategory() {
        Boolean result = categoryDao.delete(999L);
        assertFalse(result);
    }

    @Test
    public void update_NonExistentCategory() {
        Category category = getCategory();
        Optional<Category> result = categoryDao.update(999L, category);
        assertFalse(result.isPresent());
    }

    @Test
    public void insertCategory_DuplicateName() {
        Category category1 = getCategory();
        Category category2 = getCategory();
        categoryDao.insert(category1);
        assertThrows(ConstraintViolationException.class, () -> {
            categoryDao.insert(category2);
        });
    }

    @Test
    public void insertCategory_NullName() {
        Category category = getCategory();
        category.setName(null);
        assertThrows(PropertyValueException.class, () -> {
            categoryDao.insert(category);
        });
    }

}