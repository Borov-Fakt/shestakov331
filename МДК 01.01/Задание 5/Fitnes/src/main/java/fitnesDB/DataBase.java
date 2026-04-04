package fitnesDB;

import java.sql.*;

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

    public String[] checkLogin(String log, String pass) {
        try {
            Connection conn = getDbConnection();
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM users WHERE login = '" + log + "' AND password = '" + pass + "'");
            if (res.next()) {
                return new String[]{res.getString("role"), String.valueOf(res.getInt("client_id")), res.getString("first_name")};
            }
        } catch (Exception e) {
            System.out.println("Ошибка входа: " + e.getMessage());
        }
        return null;
    }

    public void registerAnyUser(String fName, String lName, int year, String gender, String contacts, String role, String log, String pass) {
        Connection conn = null;
        try {
            conn = getDbConnection();
            conn.setAutoCommit(false);
            Statement statement = conn.createStatement();

            String clientIdStr = "NULL";
            if (role.equals("CLIENT")) {
                String sqlClient = "INSERT INTO clients (first_name, last_name, birth_year, gender, contact_info) " +
                        "VALUES ('" + fName + "', '" + lName + "', " + year + ", '" + gender + "', '" + contacts + "') RETURNING id";
                ResultSet res = statement.executeQuery(sqlClient);
                if (res.next()) clientIdStr = String.valueOf(res.getInt("id"));
            }

            String sqlUser = "INSERT INTO users (login, password, role, client_id, first_name, last_name) " +
                    "VALUES ('" + log + "', '" + pass + "', '" + role + "', " + clientIdStr + ", '" + fName + "', '" + lName + "')";
            statement.executeUpdate(sqlUser);

            conn.commit();
        } catch (Exception e) {
            System.out.println("Ошибка регистрации: " + e.getMessage());
            try { if (conn != null) conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }

    public void addTrainingPlan(int clientId, String ex, int sets, int reps) {
        try {
            Connection conn = getDbConnection();
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO training_plans (client_id, exercise, sets, reps) VALUES (" + clientId + ", '" + ex + "', " + sets + ", " + reps + ")";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Ошибка плана: " + e.getMessage());
        }
    }

    public void addSchedule(int clientId, String trainer, String date, String time) {
        try {
            Connection conn = getDbConnection();
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO schedules (client_id, trainer_name, train_date, train_time) VALUES (" + clientId + ", '" + trainer + "', '" + date + "', '" + time + "')";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Ошибка расписания: " + e.getMessage());
        }
    }

    public void recordAttendance(int clientId, String zone) {
        try {
            Connection conn = getDbConnection();
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO attendance (client_id, visit_date, zone) VALUES (" + clientId + ", CURRENT_DATE, '" + zone + "')";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Ошибка посещения: " + e.getMessage());
        }
    }

    public String getAttendanceReport() {
        StringBuilder sb = new StringBuilder();
        try {
            Connection conn = getDbConnection();
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery("SELECT u.first_name, a.visit_date, a.zone FROM attendance a JOIN users u ON a.client_id = u.client_id");
            while (res.next()) {
                sb.append(res.getString("first_name")).append(" | ").append(res.getDate("visit_date")).append(" | ").append(res.getString("zone")).append("\n");
            }
        } catch (Exception e) {
            System.out.println("Ошибка отчета: " + e.getMessage());
        }
        return sb.toString();
    }

    public String getPeopleList(String role) {
        StringBuilder sb = new StringBuilder();
        try {
            Connection conn = getDbConnection();
            Statement statement = conn.createStatement();
            String sql = role.equals("ADMIN") ? "SELECT * FROM users" : "SELECT * FROM users WHERE role = 'CLIENT'";
            ResultSet res = statement.executeQuery(sql);
            sb.append("ID | Логин | Роль | Имя\n");
            while (res.next()) {
                int id = res.getString("role").equals("CLIENT") ? res.getInt("client_id") : res.getInt("id");
                sb.append(id).append(" | ").append(res.getString("login")).append(" | ").append(res.getString("role")).append(" | ").append(res.getString("first_name")).append("\n");
            }
        } catch (Exception e) {
            System.out.println("Ошибка списка: " + e.getMessage());
        }
        return sb.toString();
    }

    public String getClientDataText(int clientId, String table) {
        StringBuilder sb = new StringBuilder();
        try {
            Connection conn = getDbConnection();
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM " + table + " WHERE client_id = " + clientId);
            while (res.next()) {
                if (table.equals("training_plans"))
                    sb.append(res.getString("exercise")).append(": ").append(res.getInt("sets")).append("x").append(res.getInt("reps")).append("\n");
                else
                    sb.append(res.getDate("train_date")).append(" в ").append(res.getTime("train_time")).append("\n");
            }
        } catch (Exception e) {
            System.out.println("Ошибка данных клиента: " + e.getMessage());
        }
        return sb.toString();
    }
}