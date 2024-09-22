package com.online.shop.repository.impl;

import com.online.shop.entity.User_;
import com.online.shop.repository.AbstractDao;
import com.online.shop.entity.User;
import com.online.shop.repository.UserDao;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static com.online.shop.utils.StringConst.*;

@Slf4j
@Repository
@Transactional
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public UserDaoImpl() {
        setClazz(User.class);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        log.info("Executing findByEmail method by {}", email);
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException(EXCEPTION_EMAIL_CANNOT_BE_NULL_OR_EMPTY);
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(criteriaBuilder.equal(root.get(User_.email), email));
        List<User> result = entityManager.createQuery(query).getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    @Override
    public List<User> findByCreationDate(String createdAt) {
        log.info("Executing findByCreatedAt method by {}", createdAt);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate date = LocalDate.parse(createdAt, formatter);
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        Predicate datePredicate = criteriaBuilder.between(root.get(User_.createdAt), startOfDay, endOfDay);
        query.where(datePredicate);
        return entityManager.createQuery(query).getResultList();
    }

}