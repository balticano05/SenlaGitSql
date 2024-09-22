package com.online.shop.repository.impl;

import com.online.shop.entity.Course;
import com.online.shop.entity.Transaction;
import com.online.shop.entity.User;
import com.online.shop.repository.TransactionDao;
import com.online.shop.utils.StringConst;
import com.online.shop.сontext.AppConfig;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
class TransactionDaoTest {

    @Resource
    private TransactionDao transactionDao;

    private Transaction getTransaction() {
        Transaction transaction = new Transaction();
        User user = new User();
        user.setId(1L);
        user.setEmail("admin1.shopcourses@gmail.com");
        transaction.setUser(user);
        Course course = new Course();
        course.setId(11L);
        transaction.setCourse(course);
        transaction.setDateTime(LocalDateTime.now());
        transaction.setPrice(BigDecimal.valueOf(100.00));
        return transaction;
    }

    @Test
    void getById() {
        Transaction transaction = getTransaction();
        Long transactionId = transactionDao.insert(transaction);
        Optional<Transaction> transactionResult = transactionDao.getById(transactionId);
        assertTrue(transactionResult.isPresent());
        assertEquals(transaction.getPrice(), transactionResult.get().getPrice());
    }

    @Test
    void getAll() {
        List<Transaction> transactions = transactionDao.getAll();
        assertFalse(transactions.isEmpty());
    }

    @Test
    void insert() {
        Transaction transaction = getTransaction();
        Long transactionId = transactionDao.insert(transaction);
        Optional<Transaction> transactionResult = transactionDao.getById(transactionId);
        assertTrue(transactionResult.isPresent());
        assertEquals(transaction.getPrice(), transactionResult.get().getPrice());
    }

    @Test
    void update() {
        Transaction transaction = getTransaction();
        Long transactionId = transactionDao.insert(transaction);
        Optional<Transaction> foundTransaction = transactionDao.getById(transactionId);
        assertTrue(foundTransaction.isPresent());
        Transaction updatedTransaction = foundTransaction.get();
        updatedTransaction.setPrice(BigDecimal.valueOf(200.00));
        transactionDao.update(transactionId, updatedTransaction);
        Optional<Transaction> transactionResult = transactionDao.getById(transactionId);
        assertTrue(transactionResult.isPresent());
        assertEquals(updatedTransaction.getPrice(), transactionResult.get().getPrice());
    }

    @Test
    void delete() {
        Transaction transaction = getTransaction();
        Long transactionId = transactionDao.insert(transaction);
        transactionDao.delete(transactionId);
        Optional<Transaction> transactionResult = transactionDao.getById(transactionId);
        assertFalse(transactionResult.isPresent());
    }

    @Test
    void findTransactionsByEmail() {
        Transaction transaction = getTransaction();
        transactionDao.insert(transaction);
        List<Transaction> transactions = transactionDao.findTransactionsByEmail(transaction.getUser().getEmail());
        assertFalse(transactions.isEmpty());
        assertEquals(transaction.getUser().getEmail(), transactions.get(0).getUser().getEmail());
    }

    @Test
    void findByCreatedAt() {
        Transaction transaction = getTransaction();
        transactionDao.insert(transaction);
        String createdAt = transaction.getDateTime().format(DateTimeFormatter.ofPattern(StringConst.DATE_FORMAT));
        List<Transaction> transactions = transactionDao.findByCreatedAt(createdAt);
        assertFalse(transactions.isEmpty());
        assertEquals(1, transactions.size());
        assertEquals(transaction.getPrice(), transactions.get(0).getPrice());
    }

}