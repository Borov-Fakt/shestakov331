package bankDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankDataBase {
    private final String host = "localhost";
    private final String port = "5432";
    private final String dbName = "bank";
    private final String login = "postgres";
    private final String password = "1234";

    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String str = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(str, login, password);
    }

    public void addAccount(Account account) {
        try (Connection conn = getDbConnection(); Statement statement = conn.createStatement()) {
            String sql = "INSERT INTO accounts (full_name, account_number, balance, account_type, bonus_applied) " +
                    "VALUES ('" + account.getFullName() + "', '" + account.getAccountNumber() + "', " +
                    account.getBalance() + ", '" + account.getType().name() + "', " + account.isWelcomeBonusApplied() + ")";
            statement.executeUpdate(sql);
            System.out.println("Данные сохранены в БД.");
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Ошибка БД (addAccount): " + e.getMessage());
        }
    }

    public Account getAccountByNumber(String accountNumber) {
        try (Connection conn = getDbConnection(); Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM accounts WHERE account_number = '" + accountNumber + "'");
            if (rs.next()) {
                return mapRowToAccount(rs);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Ошибка БД (getAccount): " + e.getMessage());
        }
        return null;
    }

    public List<Account> getAllAccounts() {
        List<Account> list = new ArrayList<>();
        try (Connection conn = getDbConnection(); Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM accounts");
            while (rs.next()) {
                list.add(mapRowToAccount(rs));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Ошибка БД (getAll): " + e.getMessage());
        }
        return list;
    }

    public void updateAccountState(Account account) {
        try (Connection conn = getDbConnection(); Statement statement = conn.createStatement()) {
            String sql = "UPDATE accounts SET balance = " + account.getBalance() +
                    ", bonus_applied = " + account.isWelcomeBonusApplied() +
                    " WHERE account_number = '" + account.getAccountNumber() + "'";
            statement.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Ошибка обновления в БД: " + e.getMessage());
        }
    }

    public void transferTransaction(String fromAcc, String toAcc, double amount) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            conn = getDbConnection();
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT balance FROM accounts WHERE account_number = '" + fromAcc + "'");
            if (!rs.next()) throw new SQLException("Отправитель не найден");
            double currentBalance = rs.getDouble("balance");

            if (currentBalance < amount) throw new SQLException("Недостаточно средств");

            stmt.executeUpdate("UPDATE accounts SET balance = balance - " + amount + " WHERE account_number = '" + fromAcc + "'");
            int rows = stmt.executeUpdate("UPDATE accounts SET balance = balance + " + amount + " WHERE account_number = '" + toAcc + "'");

            if (rows == 0) throw new SQLException("Получатель не найден");

            conn.commit();
            System.out.println("Транзакция в БД прошла успешно.");
        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    private Account mapRowToAccount(ResultSet rs) throws SQLException {
        String name = rs.getString("full_name");
        String accNum = rs.getString("account_number");
        double balance = rs.getDouble("balance");
        String typeStr = rs.getString("account_type");
        boolean bonus = rs.getBoolean("bonus_applied");

        AccountType type = AccountType.valueOf(typeStr);
        return new Account(name, accNum, balance, type, bonus);
    }
}

