package com.online.shop.repository.impl;

import com.online.shop.entity.Course;
import com.online.shop.repository.CourseDao;

import java.util.List;

public class CourseDaoImpl implements CourseDao<Course> {

    private List<Course> courses;

    @Override
    public Course findById(Long id) {
        return null;
    }

    @Override
    public List<Course> getAll() {
        return List.of();
    }

    @Override
    public void add(Course entity) {

    }

    @Override
    public void update(Long id, Course entity) {

    }

    @Override
    public void delete(Long entity) {

    }
}
