package com.online.shop.repository.impl;

import com.online.shop.entity.Review;
import com.online.shop.entity.Review_;
import com.online.shop.entity.User;
import com.online.shop.entity.User_;
import com.online.shop.repository.AbstractDao;
import com.online.shop.repository.ReviewDao;
import com.online.shop.utils.StringConst;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.online.shop.Application.log;
import static com.online.shop.utils.StringConst.*;

@Repository
@Transactional
public class ReviewDaoImpl extends AbstractDao<Review> implements ReviewDao {

    public ReviewDaoImpl() {
        setClazz(Review.class);
    }

    @Override
    public List<Review> findByEmail(String email) {
        log.info(LOG_EXECUTING_FIND_BY_EMAIL_METHOD, email);
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException(EXCEPTION_EMAIL_CANNOT_BE_NULL_OR_EMPTY);
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Review> query = criteriaBuilder.createQuery(Review.class);
        Root<Review> root = query.from(Review.class);
        Join<Review, User> userJoin = root.join(Review_.user);
        query.select(root).where(criteriaBuilder.equal(userJoin.get(User_.email), email));
        List<Review> result = entityManager.createQuery(query).getResultList();
        return result;
    }

    @Override
    public List<Review> findByCreatedAt(String createdAt) {
        log.info(LOG_EXECUTING_FIND_BY_CREATED_AT_METHOD, createdAt);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(StringConst.DATE_FORMAT);
        LocalDate date = LocalDate.parse(createdAt, formatter);
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay().minusNanos(1);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Review> query = criteriaBuilder.createQuery(Review.class);
        Root<Review> root = query.from(Review.class);
        Predicate datePredicate = criteriaBuilder.between(root.get(Review_.createdAt), startOfDay, endOfDay);
        query.where(datePredicate);
        return entityManager.createQuery(query).getResultList();
    }

}