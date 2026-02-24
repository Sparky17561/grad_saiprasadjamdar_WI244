package com.layoutapp.config;

import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/layoutmanagement"; 
    private static final String USER = "postgres";
    private static final String PASSWORD = "abc123"; // Update Password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}