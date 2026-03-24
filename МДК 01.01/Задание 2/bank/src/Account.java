public class Account {

    private String fullName;
    private String accountNumber;
    private double balance;
    private boolean welcomeBonusApplied;
    private AccountType type;

    public Account(String fullName, String accountNumber, double initialBalance, AccountType type) {
        this.fullName = fullName;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.type = type;
        this.welcomeBonusApplied = false;
    }

    public String getFullName() {
        return fullName;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.err.println("Ошибка: Сумма пополнения должна быть больше нуля.");
            return;
        }
        this.balance += amount;
    }

    public void withdraw(double amount) throws Ex {
        if (amount <= 0) {
            System.err.println("Ошибка: Сумма снятия должна быть больше нуля.");
            return;
        }
        if (this.balance < amount) {
            throw new Ex("Недостаточно средств. На счете: " + String.format("%.2f", balance));
        }
        this.balance -= amount;
    }

    public void transferTo(Account recipient, double amount) {
        System.out.println("\nПеревод " + String.format("%.2f", amount) + " руб от " + this.fullName + " к " + recipient.fullName);
        try {
            this.withdraw(amount);
            recipient.deposit(amount);
            System.out.println("Перевод успешно выполнен.");
        } catch (Ex e) {
            System.err.println("Ошибка перевода: " + e.getMessage());
        }
    }

    public void payService(double serviceCost) {
        System.out.println("\nОплата услуги со счета " + this.accountNumber + " на сумму " + String.format("%.2f", serviceCost) + "руб");
        try {
            this.withdraw(serviceCost);
            System.out.println("Услуга успешно оплачена. Новый баланс: " + String.format("%.2f", this.balance) + " руб");
            calculateCashback(serviceCost);
            applyWelcomeBonus();
        } catch (Ex e) {
            System.err.println("Ошибка оплаты: " + e.getMessage());
        }
    }

    public void applyMonthlyFee() {
        if (this.type == AccountType.STANDARD) {
            System.out.println("\nСписание ежемесячной платы (100 руб) со счета " + this.accountNumber);
            try {
                this.withdraw(100);
                System.out.println("Плата успешно списана. Новый баланс: " + String.format("%.2f", this.balance));
            } catch (Ex e) {
                System.err.println("Не удалось списать плату: " + e.getMessage());
            }
        }
    }

    private void calculateCashback(double paymentAmount) {
        double cashbackRate = 0.0;
        switch (this.type) {
            case STANDARD:
                if (paymentAmount >= 10000) cashbackRate = 0.01;
                break;
            case PREMIUM:
                if (paymentAmount >= 10000) cashbackRate = 0.05;
                break;
            case VIP:
                if (paymentAmount >= 100000) cashbackRate = 0.10;
                else if (paymentAmount >= 10000) cashbackRate = 0.05;
                else cashbackRate = 0.01;
                break;
        }

        if (cashbackRate > 0) {
            double cashbackAmount = paymentAmount * cashbackRate;
            System.out.println("Начислен кешбек: " + String.format("%.2f", cashbackAmount) + " руб");
            this.deposit(cashbackAmount);
        }
    }

    private void applyWelcomeBonus() {
        if (!welcomeBonusApplied) {
            System.out.println("Начислен приветственный бонус: 1000.00 руб.");
            this.deposit(1000);
            this.welcomeBonusApplied = true;
        }
    }

    public String getAccountType() {
        return switch (type) {
            case STANDARD -> "Базовый";
            case PREMIUM -> "Премиум";
            case VIP -> "VIP";

        };
    }

    @Override
    public String toString() {
        return "Клиент: '" + fullName + '\'' +
                ", Тип: " + getAccountType() +
                ", Номер счета: '" + accountNumber + '\'' +
                ", Баланс: " + String.format("%.2f", balance) + " руб";
    }
}
