package com.online.shop.service.impl;

import com.online.shop.service.CategoryService;
import com.online.shop.dto.CategoryDto;
import com.online.shop.entity.Category;
import com.online.shop.repository.CategoryDao;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;
    private final ModelMapper modelMapper;

    @Override
    public Long insert(CategoryDto entityDto) {
        log.info("Executing insert method in CategoryServiceImpl with DTO: {}", entityDto);
        if (entityDto == null) {
            log.error("CategoryDto is null in insert method");
            throw new IllegalArgumentException("CategoryDto cannot be null");
        }
        return categoryDao.insert(modelMapper.map(entityDto, Category.class));
    }

    @Override
    public CategoryDto update(Long id, CategoryDto entityDto) {
        log.info("Executing update method in CategoryServiceImpl for ID: {} with DTO: {}", id, entityDto);
        if (id == null) {
            log.error("ID is null in update method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (entityDto == null) {
            log.error("CategoryDto is null in update method");
            throw new IllegalArgumentException("CategoryDto cannot be null");
        }
        Category updatedCategory = categoryDao.update(id, modelMapper.map(entityDto, Category.class))
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        return modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto findById(Long id) {
        log.info("Executing findById method in CategoryServiceImpl for ID: {}", id);
        if (id == null) {
            log.error("ID is null in findById method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        Category foundCategory = categoryDao.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        return modelMapper.map(foundCategory, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAll(PageRequest pageRequest) {
        log.info("Executing getAll method in CategoryServiceImpl");
        return categoryDao.getAll(pageRequest).stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Executing delete method in CategoryServiceImpl for ID: {}", id);
        if (id == null) {
            log.error("ID is null in delete method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        return categoryDao.delete(id);
    }

}