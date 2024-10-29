package com.online.shop.repository;

import com.online.shop.entity.CoursePlan;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface CoursePlanDao {
    Optional<CoursePlan> getById(Long id);

    List<CoursePlan> getAll(PageRequest pageRequest);

    Long insert(CoursePlan entity);

    Optional<CoursePlan> update(Long id, CoursePlan entity);

    Boolean delete(Long id);
}