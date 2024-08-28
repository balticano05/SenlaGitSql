package com.online.shop.repository.impl;

import com.online.shop.entity.Course;
import com.online.shop.repository.CourseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseDaoImpl implements CourseDao<Course> {

    private List<Course> courses;

    @Override
    public Optional<Course> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Course> getAll() {
        return List.of();
    }

    @Override
    public void insert(Course entity) {

    }

    @Override
    public void update(Long id, Course entity) {

    }

    @Override
    public void delete(Long entity) {

    }

}
