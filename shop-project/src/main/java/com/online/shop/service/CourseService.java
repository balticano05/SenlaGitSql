package com.online.shop.service;

import com.online.shop.dto.CourseDto;

import java.util.List;

public interface CourseService {
    Long insert(CourseDto entityDto);

    CourseDto update(Long id, CourseDto entityDto);

    CourseDto findById(Long id);

    List<CourseDto> getAll();

    Boolean delete(Long id);

    List<CourseDto> findByDate(String date);
}