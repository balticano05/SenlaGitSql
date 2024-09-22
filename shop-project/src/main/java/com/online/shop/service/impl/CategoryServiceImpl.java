package com.online.shop.service.impl;

import com.online.shop.service.CategoryService;
import com.online.shop.dto.CategoryDto;
import com.online.shop.entity.Category;
import com.online.shop.repository.CategoryDao;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;
    private ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao, ModelMapper modelMapper) {
        this.categoryDao = categoryDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long insert(CategoryDto entityDto) {
        log.info("Executing insert method in CategoryServiceImpl with DTO: {}", entityDto);
        return categoryDao.insert(modelMapper.map(entityDto, Category.class));
    }

    @Override
    public CategoryDto update(Long id, CategoryDto entityDto) {
        log.info("Executing update method in CategoryServiceImpl for ID: {} with DTO: {}", id, entityDto);
        return modelMapper.map(categoryDao.update(id, modelMapper.map(entityDto, Category.class)), CategoryDto.class);
    }

    @Override
    public CategoryDto findById(Long id) {
        log.info("Executing findById method in CategoryServiceImpl for ID: {}", id);
        return modelMapper.map(categoryDao.getById(id), CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAll() {
        log.info("Executing getAll method in CategoryServiceImpl");
        return categoryDao.getAll().stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Executing delete method in CategoryServiceImpl for ID: {}", id);
        return categoryDao.delete(id);
    }

}