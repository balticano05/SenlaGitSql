package com.online.shop.repository;

import com.online.shop.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseDao {
    Optional<Course> findById(Long id);

    List<Course> getAll();

    Long insert(Course entity);

    Optional<Course> update(Long id, Course entity);

    Boolean delete(Long entity);
}