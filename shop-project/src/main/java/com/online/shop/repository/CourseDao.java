package com.online.shop.repository;

import com.online.shop.entity.Course;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface CourseDao {
    Optional<Course> getById(Long id);

    List<Course> getAll(PageRequest pageRequest);

    Long insert(Course entity);

    Optional<Course> update(Long id, Course entity);

    Boolean delete(Long id);

    List<Course> findByCreateDate(String createdAt);

    List<Course> findCoursesByUserId(Long id);
}