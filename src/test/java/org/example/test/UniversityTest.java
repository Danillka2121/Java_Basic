package org.example.test;

import org.example.json.JsonManager;
import org.example.model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UniversityTest {

    @Test
    public void testJsonSerialization() throws IOException {
        // Створення університету з назвою "Львівський Політехнічний Університет" та студентом "Андрій Мельник"
        University oldUniversity = new University("Львівський Політехнічний Університет", new Student("Андрій", "Мельник", "Петрович", Human.Sex.MALE, "Л101"));

        // Створення факультету "Факультет Електроніки та Комп'ютерної Техніки" та студента "Софія Шевченко"
        Faculty faculty = new Faculty("Факультет Електроніки та Комп'ютерної Техніки", new Student("Софія", "Шевченко", "Олегівна", Human.Sex.FEMALE, "Л102"));

        // Створення кафедри "Кафедра Автоматизованих Систем Управління" та студента "Максим Ковальчук"
        Department department = new Department("Кафедра Автоматизованих Систем Управління", new Student("Максим", "Ковальчук", "Вікторович", Human.Sex.MALE, "Л103"));

        // Створення групи "АСУ-22" та студента "Вікторія Ткаченко"
        Group group = new Group("АСУ-22", new Student("Вікторія", "Ткаченко", "Анатоліївна", Human.Sex.FEMALE, "Л104"));

        // Додавання студентів до групи
        group.addStudent(new Student("Олександр", "Романенко", "Сергійович", Human.Sex.MALE, "Л105"));
        group.addStudent(new Student("Наталія", "Павленко", "Миколаївна", Human.Sex.FEMALE, "Л106"));

        // Додавання групи до кафедри, кафедри до факультету та факультету до університету
        department.addGroup(group);
        faculty.addDepartment(department);
        oldUniversity.addFaculty(faculty);

        // Збереження об'єкта oldUniversity у файл "university.json" у JSON форматі
        JsonManager.saveToFile(oldUniversity, "university.json");

        // Завантаження університету з JSON файлу в об'єкт newUniversity
        University newUniversity = JsonManager.loadFromFile("university.json");

        // Перевірка чи рівні об'єкти oldUniversity і newUniversity
        assertEquals(oldUniversity, newUniversity);

        // Вивід повідомлення про успішне проходження тесту
        System.out.println("Тест UniversityTest.testJsonSerialization пройшов успішно!");
    }
}