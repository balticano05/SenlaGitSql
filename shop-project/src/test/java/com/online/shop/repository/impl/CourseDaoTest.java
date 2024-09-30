package com.online.shop.repository.impl;

import com.online.shop.entity.Course;
import com.online.shop.repository.CourseDao;
import com.online.shop.utils.StringConst;
import com.online.shop.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = AppConfig.class)
@WebAppConfiguration
@Transactional
class CourseDaoTest {

    @Resource
    private CourseDao courseDao;

    private Course getCourse() {
        Course course = new Course();
        course.setTitle("Test course");
        course.setDescription("Test course description");
        course.setCreatedAt(LocalDateTime.of(2023, 10, 5, 14, 30, 0));
        course.setPrice(BigDecimal.valueOf(100.0));
        return course;
    }

    @Test
    void getById_CourseWasFound() {
        Course course = getCourse();
        Long courseId = courseDao.insert(course);
        Optional<Course> foundCourse = courseDao.getById(courseId);
        assertTrue(foundCourse.isPresent());
        assertEquals(course.getTitle(), foundCourse.get().getTitle());
    }

    @Test
    void getAll_CoursesWereFound() {
        List<Course> courses = courseDao.getAll();
        assertFalse(courses.isEmpty());
    }

    @Test
    void insert_CourseWasInserted() {
        Course course = getCourse();
        Long courseId = courseDao.insert(course);
        Optional<Course> courseResult = courseDao.getById(courseId);
        assertTrue(courseResult.isPresent());
        assertEquals(course.getTitle(), courseResult.get().getTitle());
    }

    @Test
    void update_CourseWasUpdated() {
        Course course = getCourse();
        Long courseId = courseDao.insert(course);
        Optional<Course> foundCourse = courseDao.getById(courseId);
        assertTrue(foundCourse.isPresent());
        Course updatedCourse = foundCourse.get();
        updatedCourse.setTitle("Updated course");
        courseDao.update(courseId, updatedCourse);
        Optional<Course> updated = courseDao.getById(courseId);
        assertTrue(updated.isPresent());
        assertEquals("Updated course", updated.get().getTitle());
    }

    @Test
    void delete_CourseWasDeleted() {
        Course course = getCourse();
        Long courseId = courseDao.insert(course);
        courseDao.delete(courseId);
        Optional<Course> courseResult = courseDao.getById(courseId);
        assertFalse(courseResult.isPresent());
    }

    @Test
    void findByCreateDate_CourseWasFound() {
        ;
        Course course = getCourse();
        courseDao.insert(course);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(StringConst.DATE_FORMAT);
        String createdAt = course.getCreatedAt().format(formatter);
        List<Course> courses = courseDao.findByCreateDate(createdAt);
        assertFalse(courses.isEmpty());
        assertEquals(1, courses.size());
        assertEquals(course.getTitle(), courses.get(0).getTitle());
    }

    @Test
    void getById_CourseNotFound() {
        Long invalidCourseId = -1L;
        Optional<Course> foundCourse = courseDao.getById(invalidCourseId);
        assertFalse(foundCourse.isPresent());
    }

    @Test
    void insert_NullCourse() {
        assertThrows(IllegalArgumentException.class, () -> {
            courseDao.insert(null);
        });
    }

    @Test
    void update_NonExistentCourse() {
        Course course = getCourse();
        Optional<Course> result = courseDao.update(-1L, course);
        assertFalse(result.isPresent());
    }

    @Test
    void delete_NonExistentCourse() {
        Boolean result = courseDao.delete(-1L);
        assertFalse(result);
    }

    @Test
    void findByCreateDate_InvalidDate() {
        String invalidDate = "invalid-date";
        assertThrows(DateTimeParseException.class, () -> {
            courseDao.findByCreateDate(invalidDate);
        });
    }

}