package com.online.shop.service.impl;

import com.online.shop.entity.CoursePlan;
import com.online.shop.service.CoursePlanService;
import com.online.shop.service.CrudService;
import org.modelmapper.ModelMapper;

import java.util.List;

public class CoursePlanServiceImpl extends CrudService<CoursePlan> implements CoursePlanService {

    public CoursePlanServiceImpl(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public CoursePlan add(CoursePlan entityDto) {
        return null;
    }

    @Override
    public CoursePlan update(Long id, CoursePlan entityDto) {
        return null;
    }

    @Override
    public CoursePlan getById(Long id) {
        return null;
    }

    @Override
    public List<CoursePlan> getAll() {
        return List.of();
    }

    @Override
    public CoursePlan delete(Long id) {
        return null;
    }
}
