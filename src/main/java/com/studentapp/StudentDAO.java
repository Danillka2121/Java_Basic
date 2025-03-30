package com.studentapp;

import java.sql.SQLException;
import java.time.Month;
import java.util.List;

public interface StudentDAO {
    List<Student> getStudentsByMonth(Month month) throws SQLException;
    List<Student> getAllStudents() throws SQLException;
}