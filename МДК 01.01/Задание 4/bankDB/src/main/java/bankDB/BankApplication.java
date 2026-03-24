package bankDB;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BankApplication {
    private final BankDataBase database;
    private final Scanner scanner;
    private final Random random;

    public BankApplication() {
        this.database = new BankDataBase();
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }

    public void run() {
        System.out.println("Подключение к БД...");

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n БАНК ");
            System.out.println("1. Показать все счета");
            System.out.println("2. Баланс по номеру");
            System.out.println("3. Перевести средства");
            System.out.println("4. Оплатить услугу");
            System.out.println("5. Списать ежемесячную плату");
            System.out.println("6. Открыть новый счет");
            System.out.println("0. Выход");
            System.out.print("Выбор: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": showAllAccounts(); break;
                case "2": showBalance(); break;
                case "3": makeTransfer(); break;
                case "4": payForService(); break;
                case "5": applyMonthlyFeesToAll(); break;
                case "6": openNewAccount(); break;
                case "0": isRunning = false; break;
                default: System.out.println("Неверный ввод.");
            }
        }
    }

    private void openNewAccount() {
        System.out.print("ФИО: ");
        String name = scanner.nextLine();
        System.out.print("Тип (1-Stand, 2-Prem, 3-VIP): ");
        String t = scanner.nextLine();
        AccountType type = switch (t) {
            case "2" -> AccountType.PREMIUM;
            case "3" -> AccountType.VIP;
            default -> AccountType.STANDARD;
        };
        System.out.print("Начальный взнос: ");
        double balance = Double.parseDouble(scanner.nextLine());

        Account newAcc = new Account(name, generateAccountNumber(), balance, type);

        database.addAccount(newAcc);
    }

    private void showAllAccounts() {
        List<Account> accounts = database.getAllAccounts();
        if (accounts.isEmpty()) System.out.println("БД пуста.");
        for (Account acc : accounts) {
            System.out.println(acc);
        }
    }

    private void showBalance() {
        System.out.print("Номер счета: ");
        Account acc = database.getAccountByNumber(scanner.nextLine());
        if (acc != null) System.out.println(acc);
        else System.out.println("Счет не найден.");
    }

    private void makeTransfer() {
        System.out.print("От кого (номер): ");
        String from = scanner.nextLine();
        System.out.print("Кому (номер): ");
        String to = scanner.nextLine();
        System.out.print("Сумма: ");
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            database.transferTransaction(from, to, amount);
        } catch (Exception e) {
            System.err.println("Ошибка перевода: " + e.getMessage());
        }
    }

    private void payForService() {
        System.out.print("Номер счета: ");
        String num = scanner.nextLine();
        Account acc = database.getAccountByNumber(num);

        if (acc != null) {
            System.out.print("Стоимость услуги: ");
            double cost = Double.parseDouble(scanner.nextLine());

            double oldBalance = acc.getBalance();
            acc.payService(cost);

            if (acc.getBalance() != oldBalance) {
                database.updateAccountState(acc);
                System.out.println("Баланс обновлен в БД.");
            }
        } else {
            System.out.println("Счет не найден.");
        }
    }

    private void applyMonthlyFeesToAll() {
        List<Account> accounts = database.getAllAccounts();
        System.out.println("Обработка ежемесячной платы...");
        for (Account acc : accounts) {
            double oldBal = acc.getBalance();
            acc.applyMonthlyFee();
            if (acc.getBalance() != oldBal) {
                database.updateAccountState(acc);
            }
        }
        System.out.println("Готово.");
    }

    private String generateAccountNumber() {
        String s = "";
        for (int i = 0; i < 10; i++) s += random.nextInt(10);
        return s;
    }
}
