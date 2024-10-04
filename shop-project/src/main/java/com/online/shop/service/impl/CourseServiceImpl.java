package com.online.shop.service.impl;

import com.online.shop.utils.Validator;
import com.online.shop.service.CourseService;
import com.online.shop.dto.CourseDto;
import com.online.shop.entity.Course;
import com.online.shop.repository.CourseDao;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;
    private final ModelMapper modelMapper;

    @Override
    public Long insert(CourseDto entityDto) {
        log.info("Executing insert method in CourseServiceImpl with DTO: {}", entityDto);
        if (entityDto == null) {
            log.error("CourseDto is null in insert method");
            throw new IllegalArgumentException("CourseDto cannot be null");
        }
        return courseDao.insert(modelMapper.map(entityDto, Course.class));
    }

    @Override
    public CourseDto update(Long id, CourseDto entityDto) {
        log.info("Executing update method in CourseServiceImpl for ID: {} with DTO: {}", id, entityDto);
        if (id == null) {
            log.error("ID is null in update method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (entityDto == null) {
            log.error("CourseDto is null in update method");
            throw new IllegalArgumentException("CourseDto cannot be null");
        }
        Course updatedCourse = courseDao.update(id, modelMapper.map(entityDto, Course.class))
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        return modelMapper.map(updatedCourse, CourseDto.class);
    }

    @Override
    public CourseDto findById(Long id) {
        log.info("Executing findById method in CourseServiceImpl for ID: {}", id);
        if (id == null) {
            log.error("ID is null in findById method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        Course foundCourse = courseDao.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        return modelMapper.map(foundCourse, CourseDto.class);
    }

    @Override
    public List<CourseDto> getAll() {
        log.info("Executing getAll method in CourseServiceImpl");
        return courseDao.getAll().stream()
                .map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Executing delete method in CourseServiceImpl for ID: {}", id);
        if (id == null) {
            log.error("ID is null in delete method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        return courseDao.delete(id);
    }

    @Override
    public List<CourseDto> findByDate(String date) {
        log.info("Executing findByDate method in CourseServiceImpl for date: {}", date);
        if (date == null || !Validator.isValidDateFormat(date)) {
            log.error("Invalid date format in findByDate method");
            throw new IllegalArgumentException("Invalid date format in findByDate method");
        }
        return Optional.ofNullable(courseDao.findByCreateDate(date))
                .filter(courses -> !courses.isEmpty())
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("List of courses is empty in findByDate method");
                })
                .stream()
                .map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
    }

}