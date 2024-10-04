package com.online.shop.service.impl;

import com.online.shop.dto.CoursePlanDto;
import com.online.shop.entity.CoursePlan;
import com.online.shop.repository.CoursePlanDao;
import com.online.shop.service.CoursePlanService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CoursePlanServiceImpl implements CoursePlanService {

    private final CoursePlanDao coursePlanDao;
    private final ModelMapper modelMapper;

    @Override
    public Long insert(CoursePlanDto entityDto) {
        log.info("Executing insert method in CoursePlanServiceImpl with DTO: {}", entityDto);
        if (entityDto == null) {
            log.error("CoursePlanDto is null in insert method");
            throw new IllegalArgumentException("CoursePlanDto cannot be null");
        }
        return coursePlanDao.insert(modelMapper.map(entityDto, CoursePlan.class));
    }

    @Override
    public CoursePlanDto update(Long id, CoursePlanDto entityDto) {
        log.info("Executing update method in CoursePlanServiceImpl for ID: {} with DTO: {}", id, entityDto);
        if (id == null) {
            log.error("ID is null in update method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (entityDto == null) {
            log.error("CoursePlanDto is null in update method");
            throw new IllegalArgumentException("CoursePlanDto cannot be null");
        }
        CoursePlan updatedCoursePlan = coursePlanDao.update(id, modelMapper.map(entityDto, CoursePlan.class))
                .orElseThrow(() -> new EntityNotFoundException("CoursePlanDto not found"));
        return modelMapper.map(updatedCoursePlan, CoursePlanDto.class);
    }

    @Override
    public CoursePlanDto findById(Long id) {
        log.info("Executing findById method in CoursePlanServiceImpl for ID: {}", id);
        if (id == null) {
            log.error("ID is null in findById method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        CoursePlan foundCoursePlan = coursePlanDao.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("CoursePlan not found"));
        return modelMapper.map(foundCoursePlan, CoursePlanDto.class);
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
        log.info("Executing delete method in CoursePlanServiceImpl for ID: {}", id);
        if (id == null) {
            log.error("ID is null in delete method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        return coursePlanDao.delete(id);
    }

}
