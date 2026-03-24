package Project3;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Map<String, String> phoneBook = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Меню телефонной книги:");
            System.out.println("1 - Ввести ИМЯ");
            System.out.println("2 - Ввести НОМЕР ТЕЛЕФОНА");
            System.out.println("3 - Вывести весь список (LIST)");
            System.out.println("0 - Выйти");
            System.out.print("Выберите цифру: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("Программа завершена.");
                break;

            } else if (choice == 1) {
                System.out.print("Введите имя: ");
                String name = scanner.nextLine();

                if (phoneBook.containsKey(name)) {
                    String phone = phoneBook.get(name);
                    System.out.println("Контакт найден: " + name + " - " + phone);
                } else {
                    System.out.print("Такого имени нет. Введите номер телефона для сохранения: ");
                    String phone = scanner.nextLine();
                    phoneBook.put(name, phone);
                    System.out.println("Контакт сохранен!");
                }

            } else if (choice == 2) {
                System.out.print("Введите номер телефона: ");
                String phone = scanner.nextLine();
                boolean isFound = false;

                for (String nameKey : phoneBook.keySet()) {
                    String savedPhone = phoneBook.get(nameKey);

                    if (savedPhone.equals(phone)) {
                        System.out.println("Контакт найден: " + nameKey + " - " + savedPhone);
                        isFound = true;
                        break;
                    }
                }

                if (isFound == false) {
                    System.out.print("Такого номера нет. Введите имя абонента для сохранения: ");
                    String name = scanner.nextLine();
                    phoneBook.put(name, phone);
                    System.out.println("Контакт сохранен!");
                }

            } else if (choice == 3) {
                if (phoneBook.size() == 0) {
                    System.out.println("Телефонная книга пуста.");
                } else {
                    System.out.println("Список абонентов (по алфавиту):");
                    for (String name : phoneBook.keySet()) {
                        String phone = phoneBook.get(name);
                        System.out.println(name + " - " + phone);
                    }
                }
            } else {
                System.out.println("Ошибка: введите 1, 2, 3 или 0.");
            }
        }
    }
}