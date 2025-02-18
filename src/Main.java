/*
Лабораторна робота номер 3. ООП.

 Створити програму що буде створювати та обробляти комплексний об'єкт під назвою
 університет(university). Програма повинна складатися з трьох частин: модель вид та контролер
 згідно з парадигмою mvc (Model View Controller).  Кожній з цих груп повинна відповідати package з
 відповідною назвою.  В моделі повинні знаходитись усі класи що відповідають за структурні підрозділи
 університету. Серед них:   університет, факультет, кафедра, група, студент, людина (Human). Усі вони повинні
 містити  назву  типу string та голову типу Human. Студент також повинен бути породжений від  Human.
 Human повинен мати поля ім'я, прізвище, побатькові та стать.  Усі поля повинні бути строковими окрім поля стать.
 Стать повинна використовувати спеціальний  enum  типу Sex(стать).
 В цій лабораторній роботі  група View Нам не потрібна.
 Що стосується групи контроллер (controller)  то вона повинна містити менеджери що дозволяють нам створити
 відповідні підрозділи наприклад  StudentCreator, FacultyCreator, GroupCreator  та інші, кожен з яких повинен
 використовувати можливості нижчого за рівнем   створювача. Програма повинна також містити клас Run, в якому
 буде знаходитись точка входу та методи, що повинні дати можливість створити університет.
 Процес створення університету повинен бути зроблений в методі createTypycalUniversity.
 В програмі активно рекомендується використовувати абстрактні класи та інтерфейси

 */
import java.util.ArrayList;
import java.util.List;

// Enum для статі
enum Sex {
    MALE, FEMALE
}

// Абстрактний клас для людей
abstract class Human {
    private String firstName;
    private String lastName;
    private String middleName;
    private Sex gender;

    public Human(String firstName, String lastName, String middleName, Sex gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.gender = gender;
    }

    public String getFullName() {
        return lastName + " " + firstName + " " + middleName;
    }
}

// Клас студент (наслідує Human)
class Student extends Human {
    public Student(String firstName, String lastName, String middleName, Sex gender) {
        super(firstName, lastName, middleName, gender);
    }
}

// Клас професор (наслідує Human)
class Professor extends Human {
    public Professor(String firstName, String lastName, String middleName, Sex gender) {
        super(firstName, lastName, middleName, gender);
    }
}

// Клас групи
class Group {
    private String name;
    private Human head;
    private List<Student> students = new ArrayList<>();

    public Group(String name, Human head) {
        this.name = name;
        this.head = head;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void printGroup() {
        System.out.println("  Група: " + name + ", Староста: " + head.getFullName());
        for (Student student : students) {
            System.out.println("    Студент: " + student.getFullName());
        }
    }
}

// Клас кафедри
class Department {
    private String name;
    private Human head;
    private List<Group> groups = new ArrayList<>();

    public Department(String name, Human head) {
        this.name = name;
        this.head = head;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void printDepartment() {
        System.out.println(" Кафедра: " + name + ", Завідувач: " + head.getFullName());
        for (Group group : groups) {
            group.printGroup();
        }
    }
}

// Клас факультету
class Faculty {
    private String name;
    private Human head;
    private List<Department> departments = new ArrayList<>();

    public Faculty(String name, Human head) {
        this.name = name;
        this.head = head;
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public void printFaculty() {
        System.out.println("Факультет: " + name + ", Декан: " + head.getFullName());
        for (Department department : departments) {
            department.printDepartment();
        }
    }
}

// Клас університету
class University {
    private String name;
    private Human rector;
    private List<Faculty> faculties = new ArrayList<>();

    public University(String name, Human rector) {
        this.name = name;
        this.rector = rector;
    }

    public void addFaculty(Faculty faculty) {
        faculties.add(faculty);
    }

    public void printUniversity() {
        System.out.println("Університет: " + name + ", Ректор: " + rector.getFullName());
        for (Faculty faculty : faculties) {
            faculty.printFaculty();
        }
    }
}

// Класи-створювачі (Creator)
class StudentCreator {
    public static Student createStudent(String firstName, String lastName, String middleName, Sex gender) {
        return new Student(firstName, lastName, middleName, gender);
    }
}

class GroupCreator {
    public static Group createGroup(String name, Human head) {
        return new Group(name, head);
    }
}

class DepartmentCreator {
    public static Department createDepartment(String name, Human head) {
        return new Department(name, head);
    }
}

class FacultyCreator {
    public static Faculty createFaculty(String name, Human head) {
        return new Faculty(name, head);
    }
}

class UniversityCreator {
    public static University createUniversity(String name, Human rector) {
        return new University(name, rector);
    }
}

// Основний клас
public class Main {
    public static void main(String[] args) {
        createTypicalUniversity();
    }

    public static void createTypicalUniversity() {
        Human rector = new Professor("Олександр", "Петров", "Іванович", Sex.MALE);
        University university = UniversityCreator.createUniversity("Київський університет", rector);

        Human dean = new Professor("Марина", "Сидоренко", "Василівна", Sex.FEMALE);
        Faculty faculty = FacultyCreator.createFaculty("Факультет комп'ютерних наук", dean);
        university.addFaculty(faculty);

        Human headOfDepartment = new Professor("Ігор", "Коваль", "Андрійович", Sex.MALE);
        Department department = DepartmentCreator.createDepartment("Кафедра програмування", headOfDepartment);
        faculty.addDepartment(department);

        Human headOfGroup = new Student("Анастасія", "Гончар", "Олегівна", Sex.FEMALE);
        Group group = GroupCreator.createGroup("ПЗ-21", headOfGroup);
        department.addGroup(group);

        Student student = StudentCreator.createStudent("Владислав", "Мельник", "Юрійович", Sex.MALE);
        group.addStudent(student);

        university.printUniversity();
    }
}


