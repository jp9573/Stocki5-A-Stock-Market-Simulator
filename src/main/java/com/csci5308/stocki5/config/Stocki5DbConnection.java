package com.csci5308.stocki5.config;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Stocki5DbConnection {
    @Value("${jdbc.url}")
    String database;

    @Value("${jdbc.username}")
    String username;

    @Value("${jdbc.password}")
    String password;

    public Connection createConnection() {
        Connection connection = null;
        System.out.println(database + username + password);
        try {
            connection = DriverManager.getConnection(database.trim(), username.trim(), password.trim());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

