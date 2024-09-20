package com.online.shop.service.impl;

import com.online.shop.service.CategoryService;
import com.online.shop.dto.CategoryDto;
import com.online.shop.entity.Category;
import com.online.shop.repository.CategoryDao;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.online.shop.Application.log;
import static com.online.shop.utils.StringConst.*;

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
        log.info(LOG_EXECUTING_INSERT_METHOD);
        return categoryDao.insert(modelMapper.map(entityDto, Category.class));
    }

    @Override
    public CategoryDto update(Long id, CategoryDto entityDto) {
        log.info(LOG_EXECUTING_UPDATE_METHOD);
        return modelMapper.map(categoryDao.update(id, modelMapper.map(entityDto, Category.class)), CategoryDto.class);
    }

    @Override
    public CategoryDto findById(Long id) {
        log.info(LOG_EXECUTING_FIND_BY_ID_METHOD);
        return modelMapper.map(categoryDao.getById(id), CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAll() {
        log.info(LOG_EXECUTING_GET_ALL_METHOD);
        return categoryDao.getAll().stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Long id) {
        log.info(LOG_EXECUTING_DELETE_METHOD);
        return categoryDao.delete(id);
    }

}