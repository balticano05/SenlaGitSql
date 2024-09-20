package com.online.shop.repository.impl;

import com.online.shop.entity.Category;
import com.online.shop.repository.CategoryDao;
import com.online.shop.utils.TestConsts;
import com.online.shop.сontext.AppConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
class CategoryDaoImplTest {

    @Resource
    private CategoryDao categoryDao;

    private Category createCategory() {
        Category category = new Category();
        category.setName(TestConsts.CATEGORY_TEST_NAME);
        category.setDescription(TestConsts.CATEGORY_TEST_DESCRIPTION);
        return category;
    }

    @Test
    public void insert_CategoryWasInserted() {
        Category category = createCategory();
        Long categoryId = categoryDao.insert(category);
        Optional<Category> categoryResult = categoryDao.getById(categoryId);
        assertTrue(categoryResult.isPresent());
        assertEquals(category.getName(), categoryResult.get().getName());
    }

    @Test
    public void getById_CategoryWasFound() {
        Category category = createCategory();
        Long categoryId = categoryDao.insert(category);
        Optional<Category> foundCategory = categoryDao.getById(categoryId);
        assertTrue(foundCategory.isPresent());
    }

    @Test
    public void delete_CategoryWasDeleted() {
        Category category = createCategory();
        Long categoryId = categoryDao.insert(category);
        categoryDao.delete(categoryId);
        Optional<Category> categoryResult = categoryDao.getById(categoryId);
        assertFalse(categoryResult.isPresent());
    }

    @Test
    public void update_CategoryWasUpdated() {
        Category category = createCategory();
        Long categoryId = categoryDao.insert(category);
        Optional<Category> foundCategory = categoryDao.getById(categoryId);
        assertTrue(foundCategory.isPresent());
        Category updatedCategory = foundCategory.get();
        updatedCategory.setName(TestConsts.CATEGORY_TEST_UPDATED_NAME);
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

}