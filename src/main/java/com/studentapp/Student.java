package com.studentapp;

import java.time.LocalDate;

public class Student {
    private int id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private LocalDate birthDate;
    private String recordBookNumber;

    // Конструктори, гетери та сетери
    public Student() {}

    public Student(int id, String lastName, String firstName, String patronymic, LocalDate birthDate, String recordBookNumber) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.recordBookNumber = recordBookNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getRecordBookNumber() {
        return recordBookNumber;
    }

    public void setRecordBookNumber(String recordBookNumber) {
        this.recordBookNumber = recordBookNumber;
    }

    @Override
    public String toString() {
        return "Студент{" +
                "id=" + id +
                ", Прізвище='" + lastName + '\'' +
                ", Ім'я='" + firstName + '\'' +
                ", По батькові='" + patronymic + '\'' +
                ", Дата народження=" + birthDate +
                ", Номер залікової книжки='" + recordBookNumber + '\'' +
                '}';
    }
}