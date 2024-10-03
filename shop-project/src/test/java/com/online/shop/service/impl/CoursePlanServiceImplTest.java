package com.online.shop.service.impl;

import com.online.shop.dto.CoursePlanDto;
import com.online.shop.entity.CoursePlan;
import com.online.shop.repository.CoursePlanDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CoursePlanServiceImplTest {

    @Mock
    private CoursePlanDao coursePlanDao;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CoursePlanServiceImpl coursePlanService;

    private CoursePlan coursePlan;
    private CoursePlanDto coursePlanDto;

    @BeforeEach
    void setUp() {
        coursePlan = new CoursePlan();
        coursePlan.setLessonCount(10);
        coursePlan.setPracticeCount(5);
        coursePlan.setDuration(30);

        coursePlanDto = new CoursePlanDto();
        coursePlanDto.setLessonCount(10);
        coursePlanDto.setPracticeCount(5);
        coursePlanDto.setDuration(30);
    }

    @Test
    void insert() {
        when(modelMapper.map(any(CoursePlanDto.class), eq(CoursePlan.class))).thenReturn(coursePlan);
        when(coursePlanDao.insert(any(CoursePlan.class))).thenReturn(1L);
        Long coursePlanId = coursePlanService.insert(coursePlanDto);
        assertEquals(1L, coursePlanId);
        verify(coursePlanDao, times(1)).insert(any(CoursePlan.class));
    }

    @Test
    void update() {
        when(modelMapper.map(any(CoursePlanDto.class), eq(CoursePlan.class))).thenReturn(coursePlan);
        when(coursePlanDao.update(anyLong(), any(CoursePlan.class))).thenReturn(Optional.of(coursePlan));
        when(modelMapper.map(any(CoursePlan.class), eq(CoursePlanDto.class))).thenReturn(coursePlanDto);
        CoursePlanDto updatedCoursePlanDto = coursePlanService.update(1L, coursePlanDto);
        assertEquals(coursePlanDto.getLessonCount(), updatedCoursePlanDto.getLessonCount());
        verify(coursePlanDao, times(1)).update(anyLong(), any(CoursePlan.class));
    }

    @Test
    void findById() {
        when(coursePlanDao.getById(anyLong())).thenReturn(Optional.of(coursePlan));
        when(modelMapper.map(any(CoursePlan.class), eq(CoursePlanDto.class))).thenReturn(coursePlanDto);
        CoursePlanDto foundCoursePlanDto = coursePlanService.findById(1L);
        assertEquals(coursePlanDto.getLessonCount(), foundCoursePlanDto.getLessonCount());
        verify(coursePlanDao, times(1)).getById(anyLong());
    }

    @Test
    void getAll() {
        when(coursePlanDao.getAll()).thenReturn(Collections.singletonList(coursePlan));
        when(modelMapper.map(any(CoursePlan.class), eq(CoursePlanDto.class))).thenReturn(coursePlanDto);
        List<CoursePlanDto> coursePlans = coursePlanService.getAll();
        assertFalse(coursePlans.isEmpty());
        assertEquals(1, coursePlans.size());
        verify(coursePlanDao, times(1)).getAll();
    }

    @Test
    void delete() {
        when(coursePlanDao.delete(anyLong())).thenReturn(true);
        Boolean result = coursePlanService.delete(1L);
        assertTrue(result);
        verify(coursePlanDao, times(1)).delete(anyLong());
    }

    @Test
    void insertNegative() {
        when(modelMapper.map(any(CoursePlanDto.class), eq(CoursePlan.class))).thenReturn(coursePlan);
        when(coursePlanDao.insert(any(CoursePlan.class))).thenThrow(new RuntimeException("Insert failed"));
        assertThrows(RuntimeException.class, () -> coursePlanService.insert(coursePlanDto));
        verify(coursePlanDao, times(1)).insert(any(CoursePlan.class));
    }

    @Test
    void updateNegative() {
        assertThrows(IllegalArgumentException.class, () -> coursePlanService.update(null, coursePlanDto));
        verify(coursePlanDao, never()).update(anyLong(), any(CoursePlan.class));
    }

    @Test
    void findByIdNegative() {
        assertThrows(IllegalArgumentException.class, () -> coursePlanService.findById(null));
        verify(coursePlanDao, never()).getById(anyLong());
    }

    @Test
    void deleteNegative() {
        when(coursePlanDao.delete(anyLong())).thenReturn(false);
        Boolean result = coursePlanService.delete(1L);
        assertFalse(result);
        verify(coursePlanDao, times(1)).delete(anyLong());
    }

}
