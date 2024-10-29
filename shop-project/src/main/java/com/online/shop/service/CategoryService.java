package com.online.shop.service;

import com.online.shop.dto.CategoryDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CategoryService {
    Long insert(CategoryDto entityDto);

    CategoryDto update(Long id, CategoryDto entityDto);

    CategoryDto findById(Long id);

    List<CategoryDto> getAll(PageRequest pageRequest);

    Boolean delete(Long id);
}