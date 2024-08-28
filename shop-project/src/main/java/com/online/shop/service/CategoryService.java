package com.online.shop.service;

import com.online.shop.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto insert(CategoryDto entityDto);

    CategoryDto  update(Long id, CategoryDto  entityDto);

    CategoryDto  findById(Long id);

    List<CategoryDto> getAll();

    Boolean delete(Long id);
}