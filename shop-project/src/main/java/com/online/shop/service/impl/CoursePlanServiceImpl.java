package com.online.shop.service.impl;

import com.online.shop.dto.CoursePlanDto;
import com.online.shop.entity.CoursePlan;
import com.online.shop.service.CoursePlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursePlanServiceImpl implements CoursePlanService {

    @Override
    public CoursePlanDto insert(CoursePlan entityDto) {
        return null;
    }

    @Override
    public CoursePlanDto update(Long id, CoursePlanDto entityDto) {
        return null;
    }

    @Override
    public CoursePlanDto findById(Long id) {
        return null;
    }

    @Override
    public List<CoursePlanDto> getAll() {
        return List.of();
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

}