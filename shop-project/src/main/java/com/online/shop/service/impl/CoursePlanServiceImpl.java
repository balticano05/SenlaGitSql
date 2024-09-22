package com.online.shop.service.impl;

import com.online.shop.service.CoursePlanService;
import com.online.shop.dto.CoursePlanDto;
import com.online.shop.entity.CoursePlan;
import com.online.shop.repository.CoursePlanDao;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class CoursePlanServiceImpl implements CoursePlanService {

    private CoursePlanDao coursePlanDao;
    private ModelMapper modelMapper;

    @Autowired
    public CoursePlanServiceImpl(CoursePlanDao coursePlanDao, ModelMapper modelMapper) {
        this.coursePlanDao = coursePlanDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long insert(CoursePlanDto entityDto) {
        if (entityDto == null) {
            log.error("CoursePlanDto is null in insert method");
            throw new IllegalArgumentException("CoursePlanDto cannot be null");
        }
        log.info("Executing insert method in CoursePlanServiceImpl with DTO: {}", entityDto);
        return coursePlanDao.insert(modelMapper.map(entityDto, CoursePlan.class));
    }

    @Override
    public CoursePlanDto update(Long id, CoursePlanDto entityDto) {
        if (id == null) {
            log.error("ID is null in update method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (entityDto == null) {
            log.error("CoursePlanDto is null in update method");
            throw new IllegalArgumentException("CoursePlanDto cannot be null");
        }
        log.info("Executing update method in CoursePlanServiceImpl for ID: {} with DTO: {}", id, entityDto);
        return modelMapper.map(coursePlanDao.update(id, modelMapper.map(entityDto, CoursePlan.class)), CoursePlanDto.class);
    }

    @Override
    public CoursePlanDto findById(Long id) {
        if (id == null) {
            log.error("ID is null in findById method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        log.info("Executing findById method in CoursePlanServiceImpl for ID: {}", id);
        return modelMapper.map(coursePlanDao.getById(id), CoursePlanDto.class);
    }

    @Override
    public List<CoursePlanDto> getAll() {
        log.info("Executing getAll method in CoursePlanServiceImpl");
        return coursePlanDao.getAll().stream()
                .map(coursePlan -> modelMapper.map(coursePlan, CoursePlanDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Long id) {
        if (id == null) {
            log.error("ID is null in delete method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        log.info("Executing delete method in CoursePlanServiceImpl for ID: {}", id);
        return coursePlanDao.delete(id);
    }

}