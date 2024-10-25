package com.online.shop.repository.impl;

import com.online.shop.entity.*;
import com.online.shop.repository.AbstractDao;
import com.online.shop.repository.TransactionDao;
import com.online.shop.utils.Validator;
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
public class TransactionDaoImpl extends AbstractDao<Transaction> implements TransactionDao {

    private List<Transaction> transactions;

    @Override
    protected Class<Transaction> getEntityClass() {
        return Transaction.class;
    }

    @Override
    public List<Transaction> findTransactionsByEmail(String email) {
        log.info("Executing findTransactionsByEmail method by {}", email);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> query = criteriaBuilder.createQuery(Transaction.class);
        Root<Transaction> root = query.from(Transaction.class);
        Join<Transaction, User> userJoin = root.join(Transaction_.user);
        query.select(root).where(criteriaBuilder.equal(userJoin.get(User_.email), email));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Transaction> findByCreateDate(String createdAt) {
        log.info("Executing findByCreationDate method by {}", createdAt);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Validator.DATE_FORMAT);
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

    @Override
    public List<Transaction> findTransactionsById(Long id) {
        log.info("Executing findTransactionsById method by {}", id);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> query = criteriaBuilder.createQuery(Transaction.class);
        Root<Transaction> root = query.from(Transaction.class);
        Join<Transaction, User> userJoin = root.join(Transaction_.user);
        Predicate userIdPredicate = criteriaBuilder.equal(userJoin.get(User_.id), id);
        query.where(userIdPredicate);
        return entityManager.createQuery(query).getResultList();
    }

}