package com.online.shop.repository.impl;

import com.online.shop.entity.Category;
import com.online.shop.repository.CategoryDao;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao<Category> {

    private List<Category> categories;

    @Override
    public Category findById(Long id) {
        return null;
    }

    @Override
    public List<Category> getAll() {
        return List.of();
    }

    @Override
    public void add(Category entity) {

    }

    @Override
    public void update(Long id, Category entity) {

    }

    @Override
    public void delete(Long entity) {

    }
}
