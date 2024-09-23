package com.online.shop.repository;

import com.online.shop.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDao {
    Optional<Category> getById(Long id);

    List<Category> getAll();

    Long insert(Category entity);

    Optional<Category> update(Long id, Category entity);

    Boolean delete(Long id);
}