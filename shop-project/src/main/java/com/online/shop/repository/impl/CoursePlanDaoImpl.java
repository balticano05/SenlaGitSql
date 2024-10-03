package com.online.shop.repository.impl;

import com.online.shop.entity.CoursePlan;
import com.online.shop.repository.AbstractDao;
import com.online.shop.repository.CoursePlanDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@Transactional
public class CoursePlanDaoImpl extends AbstractDao<CoursePlan> implements CoursePlanDao {
    @Override
    protected Class<CoursePlan> getEntityClass() {
        return CoursePlan.class;
    }
}
