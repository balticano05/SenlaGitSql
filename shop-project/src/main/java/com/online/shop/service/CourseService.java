package com.online.shop.service;

import com.online.shop.dto.CourseDto;
import com.online.shop.entity.Course;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CourseService {
    Long insert(CourseDto entityDto);

    CourseDto update(Long id, CourseDto entityDto);

    CourseDto findById(Long id);

    List<CourseDto> getAll(PageRequest pageRequest);

    Boolean delete(Long id);

    List<CourseDto> findByDate(String date);

    List<CourseDto> getCoursesByUserId(Long id);
}