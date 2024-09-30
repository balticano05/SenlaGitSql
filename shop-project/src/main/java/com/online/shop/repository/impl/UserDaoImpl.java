package com.online.shop.repository.impl;

import com.online.shop.repository.AbstractDao;
import com.online.shop.entity.User;
import com.online.shop.repository.UserDao;
import jakarta.persistence.TypedQuery;
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

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        log.info("Executing findByEmail by {}", email);
        List<User> results = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .setHint("javax.persistence.fetchgraph", entityManager.createEntityGraph(getEntityClass()))
                .getResultList();
        if (results.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(results.get(0));
        }
    }

    @Override
    public List<User> findByCreateDate(String createdAt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate date = LocalDate.parse(createdAt, formatter);
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();
        String jpql = "SELECT u FROM User u WHERE u.createdAt BETWEEN :startOfDay AND :endOfDay";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("startOfDay", startOfDay);
        query.setParameter("endOfDay", endOfDay);
        return query.getResultList();
    }

}