import java.sql.*;
import java.util.Scanner;

public class StudentApp {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/students_db";
        String user = "student_user";
        String password = "student_pass";

        String[] monthNames = {
                "Січень", "Лютий", "Березень", "Квітень", "Травень", "Червень",
                "Липень", "Серпень", "Вересень", "Жовтень", "Листопад", "Грудень"
        };

        try (Scanner scanner = new Scanner(System.in);
             Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM students WHERE MONTH(birth_date) = ?")) {

            System.out.print("Введіть номер місяця (1-12): ");
            int month = scanner.nextInt();

            if (month < 1 || month > 12) {
                System.out.println("Невірний номер місяця. Будь ласка, введіть число від 1 до 12.");
                return;
            }

            System.out.println("Ви вибрали " + month + "-й місяць: " + monthNames[month - 1]);

            statement.setInt(1, month);
            ResultSet resultSet = statement.executeQuery();

            boolean hasResults = false;
            while (resultSet.next()) {
                hasResults = true;
                System.out.println("Прізвище: " + resultSet.getString("last_name"));
                System.out.println("Ім'я: " + resultSet.getString("first_name"));
                System.out.println("По-батькові: " + resultSet.getString("patronymic"));
                System.out.println("Дата народження: " + resultSet.getDate("birth_date"));
                System.out.println("Номер залікової книжки: " + resultSet.getString("record_book_number"));
                System.out.println("------------------------------");
            }

            if (!hasResults) {
                System.out.println("Студентів, народжених у цьому місяці, не знайдено.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Помилка введення. Переконайтеся, що ввели число.");
        }
    }
}
