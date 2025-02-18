/*
Лабораторна робота номер 2. Основи.
Розробити програму, що дозволить вам створити, як з клавіатури  так і рандомно матрицю
цілих чисел типу int заданої ширини та висоти(ввести з клавіатури), але не більше 20 на 20.
Створити можливість пошуку в цій матриці мінімального і максимального елементу  та розрахунок
середнього арифметичного. Програма може бути написана в одному класі, обов'язково розбиття на методи.
Обов'язкове використання клавіатури, під час вибору ручного чи рандомного створення матриці.
Створення системи зчитування з клавіатури зробити будь-яким способом, наприклад завдяки класу Scanner.
Scanner являє собою найпростішу систему сканування клавіатури. Диапазон рандомних чисел для створення
елементів матриці повинен зверігатись в спеціальних константах.
Як завдання підвищеної складності додати  розрахунок середнього геометричного елементів матриці.
 */
import java.util.Scanner;
import java.util.Random;

public class Main {

    // Константи для діапазону випадкових чисел
    private static final int RANDOM_MIN = -100;
    private static final int RANDOM_MAX = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть кількість рядків (макс 20): ");
        int rows = validateInput(scanner, 20);
        System.out.print("Введіть кількість стовпців (макс 20): ");
        int cols = validateInput(scanner, 20);

        System.out.print("Оберіть метод заповнення матриці (1 - вручну, 2 - випадковими числами): ");
        int choice = scanner.nextInt();

        int[][] matrix = (choice == 1) ? createMatrixManually(scanner, rows, cols) : createMatrixRandomly(rows, cols);

        displayMatrix(matrix);

        int min = findMinimum(matrix);
        int max = findMaximum(matrix);
        double average = calculateAverage(matrix);

        System.out.println("Мінімальне значення в матриці: " + min);
        System.out.println("Максимальне значення в матриці: " + max);
        System.out.println("Середнє значення елементів матриці: " + average);

        scanner.close();
    }

    private static int validateInput(Scanner scanner, int max) {
        int value;
        do {
            value = scanner.nextInt();
            if (value <= 0 || value > max) {
                System.out.print("Некоректне значення. Введіть число від 1 до " + max + ": ");
            }
        } while (value <= 0 || value > max);
        return value;
    }

    private static int[][] createMatrixManually(Scanner scanner, int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        System.out.println("Введіть елементи матриці:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Елемент [" + i + "][" + j + "]: ");
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    private static int[][] createMatrixRandomly(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = RANDOM_MIN + random.nextInt(RANDOM_MAX - RANDOM_MIN + 1);
            }
        }
        return matrix;
    }

    private static void displayMatrix(int[][] matrix) {
        System.out.println("Матриця:");
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    private static int findMinimum(int[][] matrix) {
        int min = Integer.MAX_VALUE;
        for (int[] row : matrix) {
            for (int element : row) {
                if (element < min) {
                    min = element;
                }
            }
        }
        return min;
    }

    private static int findMaximum(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        for (int[] row : matrix) {
            for (int element : row) {
                if (element > max) {
                    max = element;
                }
            }
        }
        return max;
    }

    private static double calculateAverage(int[][] matrix) {
        int sum = 0;
        int count = 0;
        for (int[] row : matrix) {
            for (int element : row) {
                sum += element;
                count++;
            }
        }
        return (double) sum / count;
    }
}
