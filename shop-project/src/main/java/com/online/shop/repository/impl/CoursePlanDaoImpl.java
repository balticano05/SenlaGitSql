package com.online.shop.repository.impl;

import com.online.shop.entity.CoursePlan;
import com.online.shop.repository.CoursePlanDao;

import java.util.List;

public class CoursePlanDaoImpl implements CoursePlanDao<CoursePlan> {

    private List<CoursePlan> coursePlans;

    @Override
    public CoursePlan findById(Long id) {
        return null;
    }

    @Override
    public List<CoursePlan> getAll() {
        return List.of();
    }

    @Override
    public void add(CoursePlan entity) {

    }

    @Override
    public void update(Long id, CoursePlan entity) {

    }

    @Override
    public void delete(Long entity) {

    }
}
