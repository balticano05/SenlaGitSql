package com.online.shop.repository.impl;

import com.online.shop.config.AppConfig;
import com.online.shop.entity.Course;
import com.online.shop.entity.CoursePlan;
import com.online.shop.repository.CoursePlanDao;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = AppConfig.class)
@WebAppConfiguration
@Transactional
class CoursePlanDaoTest {

    @Resource
    private CoursePlanDao coursePlanDao;

    private CoursePlan getCoursePlan() {
        Course course = new Course();
        course.setTitle("Test Course");
        course.setDescription("Test Description");
        course.setPrice(new BigDecimal("100.00"));
        course.setCreatedAt(LocalDateTime.now());

        CoursePlan coursePlan = new CoursePlan();
        coursePlan.setLessonCount(10);
        coursePlan.setPracticeCount(5);
        coursePlan.setDuration(30);
        coursePlan.setCourse(course);
        return coursePlan;
    }

    @Test
    public void create_CoursePlanWasCreated() {
        CoursePlan coursePlan = getCoursePlan();
        Long coursePlanId = coursePlanDao.insert(coursePlan);
        Optional<CoursePlan> coursePlanResult = coursePlanDao.getById(coursePlanId);
        assertTrue(coursePlanResult.isPresent());
        assertEquals(coursePlan.getLessonCount(), coursePlanResult.get().getLessonCount());
    }

    @Test
    public void findById_CoursePlanWasFound() {
        CoursePlan coursePlan = getCoursePlan();
        Long coursePlanId = coursePlanDao.insert(coursePlan);
        Optional<CoursePlan> foundCoursePlan = coursePlanDao.getById(coursePlanId);
        assertTrue(foundCoursePlan.isPresent());
    }

    @Test
    public void delete_CoursePlanWasDeleted() {
        CoursePlan coursePlan = getCoursePlan();
        Long coursePlanId = coursePlanDao.insert(coursePlan);
        coursePlanDao.delete(coursePlanId);
        Optional<CoursePlan> coursePlanResult = coursePlanDao.getById(coursePlanId);
        assertFalse(coursePlanResult.isPresent());
    }

    @Test
    public void update_CoursePlanWasUpdated() {
        CoursePlan coursePlan = getCoursePlan();
        Long coursePlanId = coursePlanDao.insert(coursePlan);
        Optional<CoursePlan> foundCoursePlan = coursePlanDao.getById(coursePlanId);
        assertTrue(foundCoursePlan.isPresent());
        CoursePlan updatedCoursePlan = foundCoursePlan.get();
        updatedCoursePlan.setLessonCount(15);
        coursePlanDao.update(coursePlanId, updatedCoursePlan);
        Optional<CoursePlan> resultCoursePlan = coursePlanDao.getById(coursePlanId);
        assertTrue(resultCoursePlan.isPresent());
        assertEquals(updatedCoursePlan.getLessonCount(), resultCoursePlan.get().getLessonCount());
    }

    @Test
    public void getAll_CoursePlansWereFound() {
        List<CoursePlan> coursePlans = coursePlanDao.getAll(PageRequest.of(0, 10));
        assertFalse(coursePlans.isEmpty());
    }

    @Test
    public void insert_NullCoursePlan() {
        assertThrows(IllegalArgumentException.class, () -> {
            coursePlanDao.insert(null);
        });
    }

    @Test
    public void findById_NonExistentId() {
        Optional<CoursePlan> coursePlanResult = coursePlanDao.getById(9999999999999L);
        assertFalse(coursePlanResult.isPresent());
    }

    @Test
    public void delete_NonExistentCoursePlan() {
        Boolean result = coursePlanDao.delete(999L);
        assertFalse(result);
    }

    @Test
    public void update_NonExistentCoursePlan() {
        CoursePlan coursePlan = getCoursePlan();
        Optional<CoursePlan> result = coursePlanDao.update(999L, coursePlan);
        assertFalse(result.isPresent());
    }

}