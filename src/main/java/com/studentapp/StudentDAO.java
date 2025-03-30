package com.studentapp;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private Connection connection;

    public StudentDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Student> getStudentsByMonth(int month) throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE MONTH(birth_date) = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, month);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("last_name"),
                        resultSet.getString("first_name"),
                        resultSet.getString("patronymic"),
                        resultSet.getDate("birth_date").toLocalDate(),
                        resultSet.getString("record_book_number")
                );
                students.add(student);
            }
        }
        return students;
    }

    // Додайте метод для отримання всіх студентів, якщо потрібно
}