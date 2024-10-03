package com.online.shop.service.impl;

import com.online.shop.exceptions.InvalidEntityDataException;
import com.online.shop.exceptions.NotFoundEntityException;
import com.online.shop.utils.Validator;
import com.online.shop.service.CourseService;
import com.online.shop.dto.CourseDto;
import com.online.shop.entity.Course;
import com.online.shop.repository.CourseDao;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao;
    private ModelMapper modelMapper;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao, ModelMapper modelMapper) {
        this.courseDao = courseDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long insert(CourseDto entityDto) {
        log.info("Executing insert method in CourseServiceImpl with DTO: {}", entityDto);
        if (entityDto == null) {
            log.error("CourseDto is null in insert method");
            throw new IllegalArgumentException("CourseDto cannot be null");
        }
        if (entityDto.getTitle() == null || entityDto.getDescription() == null || entityDto.getPrice() == null) {
            log.error("CourseDto data's cannot be null insert method");
            throw new InvalidEntityDataException("Data are required");
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
        Optional<Course> updatedCourse = courseDao.update(id, modelMapper.map(entityDto, Course.class));
        if (!updatedCourse.isPresent()) {
            throw new NotFoundEntityException("Course not found");
        }
        return modelMapper.map(updatedCourse, CourseDto.class);
    }

    @Override
    public CourseDto findById(Long id) {
        log.info("Executing findById method in CourseServiceImpl for ID: {}", id);
        if (id == null) {
            log.error("ID is null in findById method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        Optional<Course> foundCourse = courseDao.getById(id);
        if (!foundCourse.isPresent()) {
            log.error("Course not found");
            throw new NotFoundEntityException("Course not found");
        }
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
        if (id == null) {
            log.error("ID is null in delete method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        Boolean deleted = courseDao.delete(id);
        if (!deleted) {
            log.error("Course not found");
            throw new NotFoundEntityException("Course not found");
        }
        log.info("Executing delete method in CourseServiceImpl for ID: {}", id);
        return deleted;
    }

    @Override
    public List<CourseDto> findByDate(String date) {
        log.info("Executing findByDate method in CourseServiceImpl for date: {}", date);
        if (!Validator.isValidDateFormat(date) || date == null) {
            log.error("Invalid date format in findByDate method");
            throw new IllegalArgumentException("Invalid date format in findByDate method");
        }
        List<Course> foundCourses = courseDao.findByCreateDate(date);
        if(foundCourses.isEmpty()){
            log.error("List of courses is empty in findByDate method");
            throw new NotFoundEntityException("List of courses is empty in findByDate method");
        }
        return courseDao.findByCreateDate(date).stream()
                .map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
    }


}