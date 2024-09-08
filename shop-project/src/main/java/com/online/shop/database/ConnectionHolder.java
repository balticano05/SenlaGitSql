package com.online.shop.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class ConnectionHolder {

    private static DataSource dataSource;
    private static Queue<Connection> connectionPool = new ConcurrentLinkedQueue<>();
    private ThreadLocal<Connection> threadLocalConnection = new ThreadLocal<>();

    @Autowired
    public ConnectionHolder(DataSource dataSource) {
        this.dataSource = dataSource;
        initializeConnectionPool(5);
    }

    private void initializeConnectionPool(int size) {
        for (int i = 0; i < size; i++) {
            try {
                Connection connection = dataSource.getConnection();
                connectionPool.add(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized Connection getConnection() {
        if (threadLocalConnection.get() != null) {
            return threadLocalConnection.get();
        }
        if (!connectionPool.isEmpty()) {
            return connectionPool.poll();
        } else {
            return createConnection();
        }
    }

    private Connection createConnection() {
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection == null) {
            return;
        }
        try {
            if (!connection.isClosed()) {
                connection.setAutoCommit(true);
                connectionPool.add(connection);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            threadLocalConnection.remove();
        }
    }

    public Connection getTransactionConnection() {
        threadLocalConnection.set(getConnection());
        return threadLocalConnection.get();
    }

}