package com.studentapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public List<Student> getStudentsByMonth(Month month) throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE MONTH(birth_date) = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, month.getValue());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setLastName(resultSet.getString("last_name"));
                student.setFirstName(resultSet.getString("first_name"));
                student.setPatronymic(resultSet.getString("patronymic"));
                student.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                student.setRecordBookNumber(resultSet.getString("record_book_number"));
                students.add(student);
            }
        }
        return students;
    }

    @Override
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setLastName(resultSet.getString("last_name"));
                student.setFirstName(resultSet.getString("first_name"));
                student.setPatronymic(resultSet.getString("patronymic"));
                student.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                student.setRecordBookNumber(resultSet.getString("record_book_number"));
                students.add(student);
            }
        }
        return students;
    }
}