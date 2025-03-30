package com.studentapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String URL = "jdbc:mysql://localhost:3306/students_db";
    private static final String USER = "student_user";
    private static final String PASSWORD = "student_pass";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Завантаження драйвера
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Помилка завантаження драйвера MySQL", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch (SQLException e) {
            throw new SQLException("Помилка підключення до бази даних", e);
        }
    }
}