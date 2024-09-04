package com.online.shop.aspect;

import com.online.shop.database.ConnectionHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
@Aspect
public class TransactionAspect {

    @Around("@annotation(com.online.shop.annotation.Transaction)")
    public Object aroundTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

        Connection connection = null;
        try {
            connection = ConnectionHolder.getConnection();
            connection.setAutoCommit(false);
            Object result = joinPoint.proceed();
            connection.commit();
            return result;
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }

    }

}