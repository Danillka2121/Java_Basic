package com.studentapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Month;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/students_db";
        String user = "student_user";
        String password = "student_pass";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            StudentDAO studentDAO = new StudentDAO(connection);
            List<Student> students = studentDAO.getStudentsByMonth(Month.JANUARY.getValue());
            students.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}