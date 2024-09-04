package com.online.shop.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class ConnectionHolder {

    private static final ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();
    private static DataSource dataSource;

    @Autowired
    public ConnectionHolder(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = connectionHolder.get();
        if (connection == null || connection.isClosed()) {
            connection = dataSource.getConnection();
            connectionHolder.set(connection);
        }
        return connection;
    }

    @PreDestroy
    public void closeConnection() throws SQLException {
        Connection connection = connectionHolder.get();
        if (connection != null && !connection.isClosed()) {
            connection.close();
            connectionHolder.remove();
        }
        dataSource.getConnection().close();
    }

}