package com.online.shop.repository.impl;

import com.online.shop.entity.Course;
import com.online.shop.entity.Course_;
import com.online.shop.repository.AbstractDao;
import com.online.shop.repository.CourseDao;
import com.online.shop.utils.StringConst;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Repository
@Transactional
public class CourseDaoImpl extends AbstractDao<Course> implements CourseDao {

    public CourseDaoImpl() {
        setClazz(Course.class);
    }

    @Override
    public List<Course> findByCreatedAt(String createdAt) {
        log.info("Executing findByCreatedAt method by {}", createdAt);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(StringConst.DATE_FORMAT);
        LocalDate date = LocalDate.parse(createdAt, formatter);
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> query = criteriaBuilder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        Predicate datePredicate = criteriaBuilder.between(root.get(Course_.createdAt), startOfDay, endOfDay);
        query.where(datePredicate);
        return entityManager.createQuery(query).getResultList();
    }

}