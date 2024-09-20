package com.online.shop.service.impl;

import com.online.shop.service.CourseService;
import com.online.shop.dto.CourseDto;
import com.online.shop.entity.Course;
import com.online.shop.repository.CourseDao;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.online.shop.Application.log;
import static com.online.shop.utils.StringConst.*;

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
        log.info(LOG_EXECUTING_INSERT_METHOD);
        return courseDao.insert(modelMapper.map(entityDto, Course.class));
    }

    @Override
    public CourseDto update(Long id, CourseDto entityDto) {
        log.info(LOG_EXECUTING_UPDATE_METHOD);
        return modelMapper.map(courseDao.update(id, modelMapper.map(entityDto, Course.class)), CourseDto.class);
    }

    @Override
    public CourseDto findById(Long id) {
        log.info(LOG_EXECUTING_FIND_BY_ID_METHOD);
        return modelMapper.map(courseDao.getById(id), CourseDto.class);
    }

    @Override
    public List<CourseDto> getAll() {
        log.info(LOG_EXECUTING_GET_ALL_METHOD);
        return courseDao.getAll().stream()
                .map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Long id) {
        log.info(LOG_EXECUTING_DELETE_METHOD);
        return courseDao.delete(id);
    }

    @Override
    public List<CourseDto> findByDate(String date) {
        log.info(LOG_EXECUTION_FIND_BY_DATE);
        return courseDao.findByCreatedAt(date).stream()
                .map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
    }

}