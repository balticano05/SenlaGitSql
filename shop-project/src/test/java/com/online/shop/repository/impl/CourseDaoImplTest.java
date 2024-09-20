package com.online.shop.repository.impl;

import com.online.shop.entity.Course;
import com.online.shop.repository.CourseDao;
import com.online.shop.utils.StringConst;
import com.online.shop.utils.TestConsts;
import com.online.shop.сontext.AppConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
class CourseDaoImplTest {

    @Resource
    private CourseDao courseDao;

    private Course createCourse() {
        Course course = new Course();
        course.setTitle(TestConsts.COURSE_TEST_NAME);
        course.setDescription(TestConsts.COURSE_TEST_DESCRIPTION);
        course.setCreatedAt(LocalDateTime.of(2023, 10, 5, 14, 30, 0));
        course.setPrice(BigDecimal.valueOf(100.0));
        return course;
    }


    @Test
    void getById() {
        Course course = createCourse();
        Long courseId = courseDao.insert(course);
        Optional<Course> foundCourse = courseDao.getById(courseId);
        assertTrue(foundCourse.isPresent());
        assertEquals(course.getTitle(), foundCourse.get().getTitle());
    }

    @Test
    void getAll() {
        List<Course> courses = courseDao.getAll();
        assertFalse(courses.isEmpty());
    }

    @Test
    void insert() {
        Course course = createCourse();
        Long courseId = courseDao.insert(course);
        Optional<Course> courseResult = courseDao.getById(courseId);
        assertTrue(courseResult.isPresent());
        assertEquals(course.getTitle(), courseResult.get().getTitle());
    }

    @Test
    void update() {
        Course course = createCourse();
        Long courseId = courseDao.insert(course);
        Optional<Course> foundCourse = courseDao.getById(courseId);
        assertTrue(foundCourse.isPresent());
        Course updatedCourse = foundCourse.get();
        updatedCourse.setTitle(TestConsts.COURSE_TEST_UPDATED_NAME);
        courseDao.update(courseId, updatedCourse);
        Optional<Course> updated = courseDao.getById(courseId);
        assertTrue(updated.isPresent());
        assertEquals(TestConsts.COURSE_TEST_UPDATED_NAME, updated.get().getTitle());
    }

    @Test
    void delete() {
        Course course = createCourse();
        Long courseId = courseDao.insert(course);
        courseDao.delete(courseId);
        Optional<Course> courseResult = courseDao.getById(courseId);
        assertFalse(courseResult.isPresent());
    }

    @Test
    void findByCreatedAt() {;
        Course course = createCourse();
        courseDao.insert(course);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(StringConst.DATE_FORMAT);
        String createdAt = course.getCreatedAt().format(formatter);
        List<Course> courses = courseDao.findByCreatedAt(createdAt);
        assertFalse(courses.isEmpty());
        assertEquals(1, courses.size());
        assertEquals(course.getTitle(), courses.get(0).getTitle());
    }

}