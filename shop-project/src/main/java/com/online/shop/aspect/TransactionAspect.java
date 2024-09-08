package com.online.shop.aspect;

import com.online.shop.database.ConnectionHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
@Aspect
public class TransactionAspect {

    private final ConnectionHolder connectionHolder;

    @Autowired
    public TransactionAspect(ConnectionHolder connectionHolder) {
        this.connectionHolder = connectionHolder;
    }

    @Around("@annotation(com.online.shop.annotation.Transaction)")
    public Object aroundTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        Connection connection = connectionHolder.getTransactionConnection();
        connection.setAutoCommit(false);
        Object result;
        try {
            result = joinPoint.proceed();
            connection.commit();
        } catch (Throwable throwable) {
            connection.rollback();
            throw throwable;
        } finally {
            connectionHolder.releaseConnection(connection);
        }
        return result;
    }

}