package com.online.shop.service;

import com.online.shop.dto.CoursePlanDto;
import com.online.shop.entity.CoursePlan;

import java.util.List;

public interface CoursePlanService {
    CoursePlanDto insert(CoursePlan entityDto);

    CoursePlanDto  update(Long id, CoursePlanDto  entityDto);

    CoursePlanDto  findById(Long id);

    List<CoursePlanDto> getAll();

    Boolean delete(Long id);
}