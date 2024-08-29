package com.online.shop.repository.impl;

import com.online.shop.entity.Course;
import com.online.shop.repository.CourseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseDaoImpl implements CourseDao {

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
    public Long insert(Course entity) {
        return 0L;
    }

    @Override
    public Optional<Course> update(Long id, Course entity) {
        return Optional.empty();
    }

    @Override
    public Boolean delete(Long entity) {
        return null;
    }

}