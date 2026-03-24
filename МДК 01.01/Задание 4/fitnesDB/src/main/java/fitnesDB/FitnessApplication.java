package fitnesDB;

import fitnesDB.enums.FitnessZone;
import fitnesDB.enums.TypeSubscription;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class FitnessApplication {
    private final DataBase db;
    private final Fitness fitness;
    private final Scanner scanner;

    public FitnessApplication() {
        this.db = new DataBase();
        this.fitness = new Fitness();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nФИТНЕС КЛУБ");
            System.out.println("1. Показать всех клиентов");
            System.out.println("2. Зарегистрировать нового клиента");
            System.out.println("3. Купить абонемент");
            System.out.println("4. Пропустить клиента в зону (Вход)");
            System.out.println("5. Перевести средства между клиентами");
            System.out.println("6. Закрыть клуб (конец дня)");
            System.out.println("0. Выход");
            System.out.print("Выбор: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": showAllClients(); break;
                case "2": registerClient(); break;
                case "3": buySubscription(); break;
                case "4": enterZone(); break;
                case "5": makeTransfer(); break;
                case "6": fitness.closeClub(); break;
                case "0": isRunning = false; break;
                default: System.out.println("Неверный ввод.");
            }
        }
    }

    private void showAllClients() {
        List<Client> clients = db.getAllClients();
        if (clients.isEmpty()) {
            System.out.println("Клиентов в базе нет.");
        } else {
            for (Client c : clients) {
                System.out.println(c);
                Subscription sub = db.getSubscriptionByClientId(c.getId());
                if (sub != null) System.out.println("   -> " + sub);
            }
        }
    }

    private void registerClient() {
        System.out.print("Имя: ");
        String fName = scanner.nextLine();
        System.out.print("Фамилия: ");
        String lName = scanner.nextLine();
        System.out.print("Год рождения: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Пол (1-Мужской, 2-Женский): ");
        String g = scanner.nextLine();
        String gender = g.equals("1") ? "MALE" : "FEMALE";
        System.out.print("Начальный баланс (руб): ");
        int balance = Integer.parseInt(scanner.nextLine());

        db.addClient(fName, lName, year, gender, balance);
        System.out.println("Клиент успешно сохранен в БД.");
    }

    private void buySubscription() {
        System.out.print("ID клиента: ");
        int id = Integer.parseInt(scanner.nextLine());

        Client c = db.getClientById(id);
        if (c == null) {
            System.out.println("Клиент не найден.");
            return;
        }

        System.out.println("Выберите тип абонемента:");
        System.out.println("1 - Разовый (1000 руб)");
        System.out.println("2 - Дневной (5000 руб)");
        System.out.println("3 - Полный  (7500 руб)");
        System.out.print("Выбор: ");
        String t = scanner.nextLine();

        String typeStr;
        LocalDate expDate;
        int price = 0;

        if (t.equals("1")) {
            typeStr = "ONETIME";
            expDate = LocalDate.now();
            price = 1000;
        } else if (t.equals("2")) {
            typeStr = "DAYTIME";
            expDate = LocalDate.now().plusMonths(6);
            price = 5000;
        } else {
            typeStr = "FULLTIME";
            expDate = LocalDate.now().plusYears(1);
            price = 7500;
        }

        if (c.getBalance() < price) {
            System.out.println("Ошибка: Недостаточно средств!");
            System.out.println("Баланс клиента: " + c.getBalance() + " руб. Стоимость: " + price + " руб.");
            return;
        }

        db.buySubscriptionTransaction(id, typeStr, LocalDate.now(), expDate, price);
    }

    private void enterZone() {
        System.out.print("ID клиента: ");
        int id = Integer.parseInt(scanner.nextLine());

        Subscription sub = db.getSubscriptionByClientId(id);
        if (sub == null) {
            System.out.println("У клиента нет действующего абонемента в базе!");
            return;
        }

        System.out.println("Куда идет клиент?");
        System.out.println("1. Тренажерный зал");
        System.out.println("2. Бассейн");
        System.out.println("3. Групповые занятия");
        System.out.print("Выбор: ");
        String z = scanner.nextLine();

        FitnessZone zone;
        if (z.equals("1")) zone = FitnessZone.GYM;
        else if (z.equals("2")) zone = FitnessZone.SWIMPOOL;
        else zone = FitnessZone.GROUP_EXERCISE;

        fitness.processSubscription(sub, zone);
    }

    private void makeTransfer() {
        System.out.print("От кого (ID клиента): ");
        int from = Integer.parseInt(scanner.nextLine());
        System.out.print("Кому (ID клиента): ");
        int to = Integer.parseInt(scanner.nextLine());
        System.out.print("Сумма перевода: ");
        int amount = Integer.parseInt(scanner.nextLine());

        db.transferMoney(from, to, amount);
    }
}