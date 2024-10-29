package com.online.shop.service;

import com.online.shop.dto.CoursePlanDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CoursePlanService {
    Long insert(CoursePlanDto entityDto);

    CoursePlanDto update(Long id, CoursePlanDto entityDto);

    CoursePlanDto findById(Long id);

    List<CoursePlanDto> getAll(PageRequest pageRequest);

    Boolean delete(Long id);
}