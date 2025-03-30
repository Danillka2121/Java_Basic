package com.studentapp;

import java.sql.SQLException;
import java.time.Month;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAOImpl();
        try {
            List<Student> students = studentDAO.getStudentsByMonth(Month.JANUARY);
            System.out.println("Студенти, народжені в січні:");
            for (Student student : students) {
                System.out.println(student);
            }

            students = studentDAO.getAllStudents();
            System.out.println("\nВсі студенти:");
            for (Student student : students) {
                System.out.println(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}