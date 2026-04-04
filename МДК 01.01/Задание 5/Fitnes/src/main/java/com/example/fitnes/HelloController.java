package com.example.fitnes;

import fitnesDB.DataBase;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class HelloController {
    private final DataBase db = new DataBase();
    private String role;
    private int clientId;
    private String uName;

    @FXML private HBox loginPane;
    @FXML private TextField loginField, passwordField;
    @FXML private Label statusLabel;
    @FXML private TabPane mainTabPane;

    @FXML private Tab adminTab, trainerTab, clientTab;

    @FXML private TextField fNameF, lNameF, yearF, contactF, regLoginF, regPassF;
    @FXML private ComboBox<String> genderBox, roleBox;
    @FXML private TextArea allUsersArea, reportArea;

    @FXML private TextField targetClientId, exName, exSets, exReps, schedTime;
    @FXML private DatePicker schedDate;
    @FXML private TextArea trainerClientsArea;

    @FXML private Label clientWelcome;
    @FXML private TextArea clientPlanArea, clientSchedArea;

    @FXML
    protected void handleLogin() {
        String[] data = db.checkLogin(loginField.getText(), passwordField.getText());
        if (data != null) {
            role = data[0];
            clientId = Integer.parseInt(data[1]);
            uName = data[2];

            loginPane.setVisible(false);
            loginPane.setManaged(false);
            mainTabPane.setVisible(true);
            mainTabPane.setManaged(true);
            statusLabel.setText("Вы вошли как: " + uName);

            adminTab.setDisable(!role.equals("ADMIN"));
            trainerTab.setDisable(role.equals("CLIENT"));
            clientTab.setDisable(!role.equals("CLIENT"));

            refreshUserLists();
        } else {
            statusLabel.setText("Ошибка входа!");
        }
    }

    @FXML
    protected void refreshUserLists() {
        allUsersArea.setText(db.getPeopleList("ADMIN"));
        trainerClientsArea.setText(db.getPeopleList("TRAINER"));

        if (role != null && role.equals("CLIENT")) {
            clientPlanArea.setText(db.getClientDataText(clientId, "training_plans"));
            clientSchedArea.setText(db.getClientDataText(clientId, "schedules"));
            clientWelcome.setText("Личный кабинет: " + uName);
        }
    }

    @FXML
    protected void handleRegisterAnyUser() {
        try {
            int year = yearF.getText().isEmpty() ? 0 : Integer.parseInt(yearF.getText());
            db.registerAnyUser(
                    fNameF.getText(), lNameF.getText(), year,
                    genderBox.getValue(), contactF.getText(),
                    roleBox.getValue(), regLoginF.getText(), regPassF.getText()
            );
            statusLabel.setText("Создан пользователь: " + regLoginF.getText());
            refreshUserLists();
        } catch (Exception e) {
            statusLabel.setText("Ошибка заполнения данных!");
        }
    }

    @FXML
    protected void handleAddTrainingPlan() {
        try {
            db.addTrainingPlan(
                    Integer.parseInt(targetClientId.getText()),
                    exName.getText(),
                    Integer.parseInt(exSets.getText()),
                    Integer.parseInt(exReps.getText())
            );
            statusLabel.setText("План для ID " + targetClientId.getText() + " обновлен.");
        } catch (Exception e) {
            statusLabel.setText("Ошибка: Проверьте ID и числа!");
        }
    }

    @FXML
    protected void handleAddSchedule() {
        try {
            db.addSchedule(
                    Integer.parseInt(targetClientId.getText()),
                    uName,
                    schedDate.getValue().toString(),
                    schedTime.getText()
            );
            statusLabel.setText("Занятие добавлено в расписание.");
        } catch (Exception e) {
            statusLabel.setText("Ошибка: Проверьте дату и время!");
        }
    }

    @FXML
    protected void handleShowAttendanceReport() {
        reportArea.setText(db.getAttendanceReport());
    }

    @FXML
    protected void refreshClientView() {
        refreshUserLists();
    }
}
