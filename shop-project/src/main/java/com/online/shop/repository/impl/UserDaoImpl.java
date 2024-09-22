package com.online.shop.repository.impl;

import com.online.shop.repository.AbstractDao;
import com.online.shop.entity.User;
import com.online.shop.repository.UserDao;
import jakarta.persistence.EntityGraph;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        log.info("Executing findByEmail by {}");
        EntityGraph<?> entityGraph = entityManager.createEntityGraph(getEntityClass());
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.loadgraph", entityGraph);
        List<User> result = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .setHint("javax.persistence.loadgraph", entityGraph)
                .getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    @Override
    public List<User> findByCreationDate(String createdAt) {
        log.info("Executing findByCreationDate method by {}", createdAt);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate date = LocalDate.parse(createdAt, formatter);
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();
        String jpql = "SELECT u FROM User u WHERE u.createdAt BETWEEN :startOfDay AND :endOfDay";
        return entityManager.createQuery(jpql, User.class)
                .setParameter("startOfDay", startOfDay)
                .setParameter("endOfDay", endOfDay)
                .getResultList();
    }

}