package com.online.shop.service.impl;

import com.online.shop.dto.CoursePlanDto;
import com.online.shop.service.CoursePlanService;
import com.online.shop.service.GenericModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursePlanServiceImpl extends GenericModelMapper implements CoursePlanService<CoursePlanDto> {

    public CoursePlanServiceImpl(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public CoursePlanDto insert(CoursePlanDto entityDto) {
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
    public void delete(Long id) {

    }

}
