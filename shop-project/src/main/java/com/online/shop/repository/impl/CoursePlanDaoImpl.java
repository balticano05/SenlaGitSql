package com.online.shop.repository.impl;

import com.online.shop.entity.CoursePlan;
import com.online.shop.repository.AbstractDao;
import com.online.shop.repository.CoursePlanDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class CoursePlanDaoImpl extends AbstractDao<CoursePlan> implements CoursePlanDao {
    public CoursePlanDaoImpl() {
        setClazz(CoursePlan.class);
    }
}