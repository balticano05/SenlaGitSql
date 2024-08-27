package com.online.shop.service.impl;

import com.online.shop.entity.Category;
import com.online.shop.service.CategoryService;
import com.online.shop.service.CrudService;
import org.modelmapper.ModelMapper;

import java.util.List;

public class CategoryServiceImpl extends CrudService<Category> implements CategoryService {

    public CategoryServiceImpl(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public Category add(Category entityDto) {
        return null;
    }

    @Override
    public Category update(Long id, Category entityDto) {
        return null;
    }

    @Override
    public Category getById(Long id) {
        return null;
    }

    @Override
    public List<Category> getAll() {
        return List.of();
    }

    @Override
    public Category delete(Long id) {
        return null;
    }

}
