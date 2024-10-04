package com.online.shop.service.impl;

import com.online.shop.dto.CourseDto;
import com.online.shop.entity.Course;
import com.online.shop.repository.CourseDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    private CourseDao courseDao;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CourseServiceImpl courseService;

    private Course course;
    private CourseDto courseDto;

    @BeforeEach
    void setUp() {
        course = new Course();
        course.setTitle("Test course");
        course.setDescription("Test course description");
        course.setCreatedAt(LocalDateTime.of(2023, 10, 5, 14, 30, 0));
        course.setPrice(BigDecimal.valueOf(100.0));

        courseDto = new CourseDto();
        courseDto.setTitle("Test course");
        courseDto.setDescription("Test course description");
        courseDto.setCreatedAt(LocalDateTime.of(2023, 10, 5, 14, 30, 0));
        courseDto.setPrice(BigDecimal.valueOf(100.0));
    }

    @Test
    void insert() {
        when(modelMapper.map(any(CourseDto.class), eq(Course.class))).thenReturn(course);
        when(courseDao.insert(any(Course.class))).thenReturn(1L);
        Long courseId = courseService.insert(courseDto);
        assertEquals(1L, courseId);
        verify(courseDao, times(1)).insert(any(Course.class));
    }

    @Test
    void update() {
        doReturn(course).when(modelMapper).map(any(CourseDto.class), eq(Course.class));
        doReturn(Optional.of(course)).when(courseDao).update(anyLong(), any(Course.class));
        doReturn(courseDto).when(modelMapper).map(any(Optional.class), eq(CourseDto.class));
        CourseDto updatedCourseDto = courseService.update(1L, courseDto);
        assertEquals(courseDto.getTitle(), updatedCourseDto.getTitle());
        verify(courseDao, times(1)).update(anyLong(), any(Course.class));
    }

    @Test
    void findById() {
        when(courseDao.getById(anyLong())).thenReturn(Optional.of(course));
        when(modelMapper.map(any(Optional.class), eq(CourseDto.class))).thenReturn(courseDto);
        CourseDto foundCourseDto = courseService.findById(1L);
        assertEquals(courseDto.getTitle(), foundCourseDto.getTitle());
        verify(courseDao, times(1)).getById(anyLong());
    }

    @Test
    void getAll() {
        when(courseDao.getAll()).thenReturn(Collections.singletonList(course));
        when(modelMapper.map(any(Course.class), eq(CourseDto.class))).thenReturn(courseDto);
        List<CourseDto> courses = courseService.getAll();
        assertFalse(courses.isEmpty());
        assertEquals(1, courses.size());
        verify(courseDao, times(1)).getAll();
    }

    @Test
    void delete() {
        when(courseDao.delete(anyLong())).thenReturn(true);
        Boolean result = courseService.delete(1L);
        assertTrue(result);
        verify(courseDao, times(1)).delete(anyLong());
    }

    @Test
    void findByDate() {
        when(courseDao.findByCreateDate(anyString())).thenReturn(Collections.singletonList(course));
        when(modelMapper.map(any(Course.class), eq(CourseDto.class))).thenReturn(courseDto);
        List<CourseDto> courses = courseService.findByDate("05.10.2023");
        assertFalse(courses.isEmpty());
        assertEquals(1, courses.size());
        verify(courseDao, times(1)).findByCreateDate(anyString());
    }

    @Test
    void insertNegative() {
        when(modelMapper.map(any(CourseDto.class), eq(Course.class))).thenReturn(course);
        when(courseDao.insert(any(Course.class))).thenThrow(new RuntimeException("Insert failed"));
        assertThrows(RuntimeException.class, () -> courseService.insert(courseDto));
        verify(courseDao, times(1)).insert(any(Course.class));
    }

    @Test
    void updateNegative() {
        assertThrows(IllegalArgumentException.class, () -> courseService.update(null, courseDto));
        verify(courseDao, never()).update(anyLong(), any(Course.class));
    }

    @Test
    void findByIdNegative() {
        assertThrows(IllegalArgumentException.class, () -> courseService.findById(null));
        verify(courseDao, never()).getById(anyLong());
    }

    @Test
    void getAllNegative() {
        when(courseDao.getAll()).thenReturn(Collections.emptyList());
        List<CourseDto> courses = courseService.getAll();
        assertTrue(courses.isEmpty());
        verify(courseDao, times(1)).getAll();
    }

    @Test
    void deleteNegative() {
        when(courseDao.delete(anyLong())).thenReturn(false);
        Boolean result = courseService.delete(1L);
        assertFalse(result);
        verify(courseDao, times(1)).delete(anyLong());
    }

    @Test
    void findByDateNegative() {
        when(courseDao.findByCreateDate(anyString())).thenReturn(Collections.emptyList());
        List<CourseDto> courses = courseService.findByDate("2023-10-01");
        assertTrue(courses.isEmpty());
        verify(courseDao, times(1)).findByCreateDate(anyString());
    }

}