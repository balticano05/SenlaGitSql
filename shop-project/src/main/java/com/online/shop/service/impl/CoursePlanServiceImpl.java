package com.online.shop.service.impl;

import com.online.shop.service.CoursePlanService;
import com.online.shop.dto.CoursePlanDto;
import com.online.shop.entity.CoursePlan;
import com.online.shop.repository.CoursePlanDao;
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
        log.info(LOG_EXECUTING_INSERT_METHOD);
        return coursePlanDao.insert(modelMapper.map(entityDto, CoursePlan.class));
    }

    @Override
    public CoursePlanDto update(Long id, CoursePlanDto entityDto) {
        log.info(LOG_EXECUTING_UPDATE_METHOD);
        return modelMapper.map(coursePlanDao.update(id, modelMapper.map(entityDto, CoursePlan.class)), CoursePlanDto.class);
    }

    @Override
    public CoursePlanDto findById(Long id) {
        log.info(LOG_EXECUTING_FIND_BY_ID_METHOD);
        return modelMapper.map(coursePlanDao.getById(id), CoursePlanDto.class);
    }

    @Override
    public List<CoursePlanDto> getAll() {
        log.info(LOG_EXECUTING_GET_ALL_METHOD);
        return coursePlanDao.getAll().stream()
                .map(coursePlan -> modelMapper.map(coursePlan, CoursePlanDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Long id) {
        log.info(LOG_EXECUTING_DELETE_METHOD);
        return coursePlanDao.delete(id);
    }

}