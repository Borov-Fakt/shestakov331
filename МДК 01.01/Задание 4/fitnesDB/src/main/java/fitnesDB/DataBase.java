package fitnesDB;

import fitnesDB.enums.Gender;
import fitnesDB.enums.TypeSubscription;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private final String host = "localhost";
    private final String port = "5432";
    private final String dbName = "fitness_db";
    private final String login = "postgres";
    private final String password = "1234";

    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String str = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(str, login, password);
    }

    public void addClient(String fName, String lName, int year, String gender, int balance) {
        try {
            Connection conn = getDbConnection();
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO clients (first_name, last_name, birth_year, gender, balance) VALUES ('" + fName + "', '" + lName + "', " + year + ", '" + gender + "', " + balance + ")";
            statement.executeUpdate(sql);
        }
        catch (Exception e) {
            System.out.println("Ошибка БД: " + e.getMessage());
        }
    }

    public List<Client> getAllClients() {
        List<Client> list = new ArrayList<>();
        try {
            Connection conn = getDbConnection();
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM clients");
            while (res.next()) {
                Client c = new Client(
                        res.getInt("id"),
                        res.getString("first_name"),
                        res.getString("last_name"),
                        res.getInt("birth_year"),
                        Gender.valueOf(res.getString("gender")),
                        res.getInt("balance")
                );
                list.add(c);
            }
        }
        catch (Exception e) {
            System.out.println("Ошибка БД: " + e.getMessage());
        }
        return list;
    }

    public Client getClientById(int id) {
        try {
            Connection conn = getDbConnection();
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM clients WHERE id = " + id);
            if (res.next()) {
                Client c = new Client(res.getInt("id"), res.getString("first_name"), res.getString("last_name"), res.getInt("birth_year"), Gender.valueOf(res.getString("gender")), res.getInt("balance"));
                return c;
            }
        }
        catch (Exception e) {
            System.out.println("Ошибка БД: " + e.getMessage());
        }
        return null;
    }

    public void buySubscriptionTransaction(int clientId, String type, LocalDate regDate, LocalDate expDate, int price) {
        Connection conn = null;
        try {
            conn = getDbConnection();
            conn.setAutoCommit(false);

            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE clients SET balance = balance - " + price + " WHERE id = " + clientId);

            String sql = "INSERT INTO subscriptions (client_id, sub_type, reg_date, exp_date) VALUES (" +
                    clientId + ", '" + type + "', '" + java.sql.Date.valueOf(regDate) + "', '" + java.sql.Date.valueOf(expDate) + "')";
            statement.executeUpdate(sql);

            conn.commit();
            System.out.println("Абонемент успешно оплачен и сохранен в БД.");
        }
        catch (Exception e) {
            System.out.println("Ошибка при оформлении абонемента: " + e.getMessage());
            try { if (conn != null) conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        }
        finally {
            try {
                if (conn != null)
                { conn.setAutoCommit(true);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public Subscription getSubscriptionByClientId(int clientId) {
        try {
            Connection conn = getDbConnection();
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM subscriptions WHERE client_id = " + clientId + " ORDER BY id DESC LIMIT 1");
            if (res.next()) {
                Client owner = getClientById(clientId);
                TypeSubscription type = TypeSubscription.valueOf(res.getString("sub_type"));
                LocalDate regDate = res.getDate("reg_date").toLocalDate();
                LocalDate expDate = res.getDate("exp_date").toLocalDate();
                Subscription sub = new Subscription(res.getInt("id"), owner, type, regDate, expDate);
                return sub;
            }
        }
        catch (Exception e) {
            System.out.println("Ошибка БД: " + e.getMessage());
        }
        return null;
    }

    public void transferMoney(int idFrom, int idTo, int amount) {
        Connection conn = null;
        try {
            conn = getDbConnection();
            conn.setAutoCommit(false);
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery("SELECT balance FROM clients WHERE id = " + idFrom);

            int currentBalance = 0;
            if (res.next()) currentBalance = res.getInt("balance");

            if (currentBalance < amount) {
                System.out.println("Недостаточно средств для перевода.");
                conn.rollback();
                return;
            }

            statement.executeUpdate("UPDATE clients SET balance = balance - " + amount + " WHERE id = " + idFrom);
            statement.executeUpdate("UPDATE clients SET balance = balance + " + amount + " WHERE id = " + idTo);

            conn.commit();
            System.out.println("Перевод выполнен.");
        }
        catch (Exception e) {
            System.out.println("Ошибка транзакции: " + e.getMessage());
            try {
                if (conn != null) conn.rollback();
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

