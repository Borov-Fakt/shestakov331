package Project2;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<String> emails = new HashSet<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Меню:");
            System.out.println("1 - Добавить email");
            System.out.println("2 - Вывести список");
            System.out.println("0 - Выйти");
            System.out.print("Введите цифру: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("Программа завершена.");
                break;
            } else if (choice == 1) {
                System.out.print("Введите email: ");
                String email = scanner.nextLine();

                if (email.contains("@") && email.contains(".")) {
                    emails.add(email);
                    System.out.println("Email добавлен!");
                } else {
                    System.out.println("Ошибка! Неверный формат (нет @ или точки).");
                }

            } else if (choice == 2) {
                if (emails.size() == 0) {
                    System.out.println("Список пока пуст.");
                } else {
                    System.out.println("Сохраненные адреса:");
                    for (String email : emails) {
                        System.out.println(email);
                    }
                }
            } else {
                System.out.println("Ошибка: введите 1, 2 или 0.");
            }
        }
    }
}