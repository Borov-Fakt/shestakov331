package bankDB;

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

    public Account(String fullName, String accountNumber, double balance, AccountType type, boolean welcomeBonusApplied) {
        this.fullName = fullName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.type = type;
        this.welcomeBonusApplied = welcomeBonusApplied;
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
    public AccountType getType() {
        return type;
    }
    public boolean isWelcomeBonusApplied() {
        return welcomeBonusApplied;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.err.println("Ошибка: Сумма должна быть > 0");
            return;
        }
        this.balance += amount;
    }

    public void withdraw(double amount) throws Ex {
        if (amount <= 0) return;
        if (this.balance < amount) {
            throw new Ex("Недостаточно средств. Баланс: " + String.format("%.2f", balance));
        }
        this.balance -= amount;
    }


    public void payService(double serviceCost) {
        System.out.println("Оплата услуги...");
        try {
            this.withdraw(serviceCost);
            calculateCashback(serviceCost);
            applyWelcomeBonus();
        } catch (Ex e) {
            System.err.println("Ошибка оплаты: " + e.getMessage());
        }
    }

    public void applyMonthlyFee() {
        if (this.type == AccountType.STANDARD) {
            try {
                this.withdraw(100);
                System.out.println("Списано 100 руб.");
            } catch (Ex e) {
                System.err.println("Ошибка списания: " + e.getMessage());
            }
        }
    }

    private void calculateCashback(double paymentAmount) {
        double cashbackRate = 0.0;
        switch (this.type) {
            case STANDARD:
                if (paymentAmount >= 10000) cashbackRate = 0.01; break;
            case PREMIUM:
                if (paymentAmount >= 10000) cashbackRate = 0.05; break;
            case VIP:
                if (paymentAmount >= 100000) cashbackRate = 0.10;
                else if (paymentAmount >= 10000) cashbackRate = 0.05;
                else cashbackRate = 0.01;
                break;
        }
        if (cashbackRate > 0) {
            double amount = paymentAmount * cashbackRate;
            System.out.println("Кешбек: " + String.format("%.2f", amount));
            this.deposit(amount);
        }
    }

    private void applyWelcomeBonus() {
        if (!welcomeBonusApplied) {
            System.out.println("Приветственный бонус +1000 руб!");
            this.deposit(1000);
            this.welcomeBonusApplied = true;
        }
    }

    public String toString() {
        return "Клиент: " + fullName + " | Счёт: " + accountNumber + " | Тип: " + type + " | Баланс: " + String.format("%.2f", balance);
    }
}
