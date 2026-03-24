import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BankApplication {

    private final List<Account> accounts;
    private final Scanner scanner;
    private final Random random;

    public BankApplication() {
        this.accounts = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }

    public void run() {
        createAccounts();
        System.out.println("Добро пожаловать в консольный банк!");

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nМЕНЮ");
            System.out.println("1. Показать все счета");
            System.out.println("2. Узнать баланс по номеру счета");
            System.out.println("3. Перевести средства");
            System.out.println("4. Оплатить услугу");
            System.out.println("5. Списать ежемесячную плату (для Базовых счетов)");
            System.out.println("6. Открыть новый счет");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");
            String choiceStr = scanner.nextLine();

            try {
                int choice = Integer.parseInt(choiceStr);
                switch (choice) {
                    case 1: showAllAccounts(); break;
                    case 2: showBalance(); break;
                    case 3: makeTransfer(); break;
                    case 4: payForService(); break;
                    case 5: applyMonthlyFeesToAll(); break;
                    case 6: openNewAccount(); break;
                    case 0: isRunning = false; break;
                    default: System.err.println("Неверный выбор. Пожалуйста, введите число от 0 до 6.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Ошибка ввода. Пожалуйста, введите число.");
            }
        }
    }

    private void openNewAccount() {
        System.out.println("\nОткрытие нового счета");

        System.out.print("Введите ФИО клиента: ");
        String fullName = scanner.nextLine();

        if (fullName.trim().isEmpty()) {
            System.err.println("ОШИБКА: ФИО не может быть пустым.");
            return;
        }

        System.out.print("Выберите тип счета 1=Базовый, 2=Премиум, 3=VIP: ");
        String typeChoiceStr = scanner.nextLine();
        AccountType type;
        switch (typeChoiceStr) {
            case "1": type = AccountType.STANDARD; break;
            case "2": type = AccountType.PREMIUM; break;
            case "3": type = AccountType.VIP; break;
            default:
                System.err.println("ОШИБКА: Неверный выбор типа счета.");
                return;
        }

        System.out.print("Введите сумму начального пополнения: ");
        double initialBalance;
        try {
            initialBalance = Double.parseDouble(scanner.nextLine());
            if (initialBalance < 0) {
                System.err.println("ОШИБКА: Начальный баланс не может быть отрицательным.");
                return;
            }
        } catch (NumberFormatException e) {
            System.err.println("ОШИБКА: Введено не число.");
            return;
        }
        String accountNumber = generateAccountNumber();
        Account newAccount = new Account(fullName, accountNumber, initialBalance, type);
        this.accounts.add(newAccount);
        System.out.println("\nСчет успешно открыт!");
        System.out.println(newAccount);
    }

    private void showAllAccounts() {
        System.out.println("\nСписок всех счетов в банке");
        for (Account acc : this.accounts) {
            System.out.println(acc);
        }
    }

    private void showBalance() {
        System.out.print("Введите номер счета: ");
        Account account = findAccountByNumber(scanner.nextLine());
        if (account != null) {
            System.out.println("Баланс клиента '" + account.getFullName() + "': " + String.format("%.2f", account.getBalance()) + "руб");
        } else {
            System.err.println("Счет не найден.");
        }
    }

    private void makeTransfer() {
        System.out.print("Введите номер счета отправителя: ");
        Account sender = findAccountByNumber(scanner.nextLine());
        if (sender == null) {
            System.err.println("Счет отправителя не найден."); return;
        }
        System.out.print("Введите номер счета получателя: ");
        Account recipient = findAccountByNumber(scanner.nextLine());
        if (recipient == null) {
            System.err.println("Счет получателя не найден."); return;
        }
        System.out.print("Введите сумму перевода: ");
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            sender.transferTo(recipient, amount);
        } catch (NumberFormatException e) {
            System.err.println("Неверный формат суммы.");
        }
    }

    private void payForService() {
        System.out.print("Введите номер счета для оплаты: ");
        Account account = findAccountByNumber(scanner.nextLine());
        if (account == null) {
            System.err.println("Счет не найден."); return;
        }
        System.out.print("Введите стоимость услуги: ");
        try {
            double cost = Double.parseDouble(scanner.nextLine());
            account.payService(cost);
        } catch (NumberFormatException e) {
            System.err.println("Неверный формат стоимости.");
        }
    }

    private void applyMonthlyFeesToAll() {
        int totalAccounts = this.accounts.size();
        for (int i = 0; i < totalAccounts; i++) {
            Account currentAccount = this.accounts.get(i);
            currentAccount.applyMonthlyFee();
        }
    }

    private Account findAccountByNumber(String accountNumber) {
        int totalAccounts = this.accounts.size();
        for (int i = 0; i < totalAccounts; i++) {
            Account currentAccount = this.accounts.get(i);
            if (currentAccount.getAccountNumber().equals(accountNumber)) {
                return currentAccount;
            }
        }
        return null;
    }

    private void createAccounts() {
        for (int i = 1; i <= 5; i++) {
            this.accounts.add(new Account("Базовый Клиент " + i, generateAccountNumber(), 5000 + random.nextInt(15000), AccountType.STANDARD));
        }
        for (int i = 1; i <= 5; i++) {
            this.accounts.add(new Account("Премиум Клиент " + i, generateAccountNumber(), 50000 + random.nextInt(100000), AccountType.PREMIUM));
        }
        for (int i = 1; i <= 5; i++) {
            this.accounts.add(new Account("VIP Клиент " + i, generateAccountNumber(), 200000 + random.nextInt(800000), AccountType.VIP));
        }
    }

    private String generateAccountNumber() {
        String accountNumber = "";
        for (int i = 0; i < 20; i++) {
            int randomDigit = this.random.nextInt(10);
            accountNumber = accountNumber + randomDigit;
        }
        return accountNumber;
    }
}
