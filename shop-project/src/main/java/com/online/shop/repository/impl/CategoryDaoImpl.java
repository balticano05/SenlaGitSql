package com.online.shop.repository.impl;

import com.online.shop.entity.Category;
import com.online.shop.repository.CategoryDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    private List<Category> categories;

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Category> getAll() {
        return List.of();
    }

    @Override
    public Long insert(Category entity) {
        return 0L;
    }

    @Override
    public Optional<Category> update(Long id, Category entity) {
        return Optional.empty();
    }

    @Override
    public Boolean delete(Long entity) {
        return null;
    }

}