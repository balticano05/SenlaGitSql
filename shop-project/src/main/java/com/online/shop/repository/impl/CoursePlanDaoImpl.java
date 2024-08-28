package com.online.shop.repository.impl;

import com.online.shop.entity.CoursePlan;
import com.online.shop.repository.CoursePlanDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CoursePlanDaoImpl implements CoursePlanDao {

    private List<CoursePlan> coursePlans;

    @Override
    public Optional<CoursePlan> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<CoursePlan> getAll() {
        return List.of();
    }

    @Override
    public Long insert(CoursePlan entity) {
        return 0L;
    }

    @Override
    public Optional<CoursePlan> update(Long id, CoursePlan entity) {
        return Optional.empty();
    }

    @Override
    public Boolean delete(Long entity) {
        return null;
    }

}