package com.online.shop.service.impl;

import com.online.shop.entity.Course;
import com.online.shop.service.CourseService;
import com.online.shop.service.CrudService;
import org.modelmapper.ModelMapper;

import java.util.List;

public class CourseServiceImpl extends CrudService<Course> implements CourseService {

    public CourseServiceImpl(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public Course add(Course entityDto) {
        return null;
    }

    @Override
    public Course update(Long id, Course entityDto) {
        return null;
    }

    @Override
    public Course getById(Long id) {
        return null;
    }

    @Override
    public List<Course> getAll() {
        return List.of();
    }

    @Override
    public Course delete(Long id) {
        return null;
    }
}
