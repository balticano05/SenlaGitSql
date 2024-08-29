package com.online.shop.service.impl;

import com.online.shop.dto.CategoryDto;
import com.online.shop.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Override
    public CategoryDto insert(CategoryDto entityDto) {
        return null;
    }

    @Override
    public CategoryDto update(Long id, CategoryDto entityDto) {
        return null;
    }

    @Override
    public CategoryDto findById(Long id) {
        return null;
    }

    @Override
    public List<CategoryDto> getAll() {
        return List.of();
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

}