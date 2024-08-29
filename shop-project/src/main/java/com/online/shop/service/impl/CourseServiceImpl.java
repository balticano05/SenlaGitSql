package com.online.shop.service.impl;

import com.online.shop.dto.CourseDto;
import com.online.shop.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Override
    public CourseDto insert(CourseDto entityDto) {
        return null;
    }

    @Override
    public CourseDto update(Long id, CourseDto entityDto) {
        return null;
    }

    @Override
    public CourseDto findById(Long id) {
        return null;
    }

    @Override
    public List<CourseDto> getAll() {
        return List.of();
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

}
