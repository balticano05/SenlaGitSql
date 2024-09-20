package com.online.shop.repository.impl;

import com.online.shop.Application;
import com.online.shop.entity.*;
import com.online.shop.repository.AbstractDao;
import com.online.shop.repository.TransactionDao;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.online.shop.utils.StringConst.*;

@Repository
public class TransactionDaoImpl extends AbstractDao<Transaction> implements TransactionDao {
    private List<Transaction> transactions;

    public TransactionDaoImpl() {
        setClazz(Transaction.class);
    }

    @Override
    public List<Transaction> findTransactionsByEmail(String email) {
        Application.log.info(LOG_EXECUTION_FIND_TRANSACTIONS_BY_EMAIL_METHOD, email);
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException(EXCEPTION_EMAIL_CANNOT_BE_NULL_OR_EMPTY);
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> query = criteriaBuilder.createQuery(Transaction.class);
        Root<Transaction> root = query.from(Transaction.class);
        Join<Transaction, User> userJoin = root.join(Transaction_.user);
        query.select(root).where(criteriaBuilder.equal(userJoin.get(User_.email), email));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Transaction> findByCreatedAt(String createdAt) {
        Application.log.info(LOG_EXECUTING_FIND_BY_CREATED_AT_METHOD, createdAt);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate date = LocalDate.parse(createdAt, formatter);
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> query = criteriaBuilder.createQuery(Transaction.class);
        Root<Transaction> root = query.from(Transaction.class);
        Predicate datePredicate = criteriaBuilder.between(root.get(Transaction_.dateTime), startOfDay, endOfDay);
        query.where(datePredicate);
        return entityManager.createQuery(query).getResultList();
    }

}