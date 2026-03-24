package org.example.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML private TextField display;
    private CalculatorService service = new CalculatorService();
    private String operator = "";
    private double firstNum = 0;
    private boolean start = true;

    @FXML
    protected void onNumClick(ActionEvent event) {
        String val = ((Button)event.getSource()).getText();
        if (start) { display.setText(val); start = false; }
        else { display.setText(display.getText() + val); }
    }

    @FXML
    protected void onOpClick(ActionEvent event) {
        operator = ((Button)event.getSource()).getText();
        firstNum = Double.parseDouble(display.getText());
        start = true;
    }

    @FXML
    protected void onEqualsClick() {
        try {
            double secondNum = Double.parseDouble(display.getText());
            double result = switch (operator) {
                case "+" -> service.add(firstNum, secondNum);
                case "-" -> service.subtract(firstNum, secondNum);
                case "*" -> service.multiply(firstNum, secondNum);
                case "/" -> service.divide(firstNum, secondNum);
                case "^" -> service.power(firstNum, secondNum);
                default -> 0;
            };
            display.setText(String.valueOf(result));
            start = true;
        } catch (Exception e) { display.setText("Ошибка"); }
    }

    @FXML protected void onSqrtClick() {
        try {
            double val = Double.parseDouble(display.getText());
            display.setText(String.valueOf(service.sqrt(val)));
            start = true;
        } catch (Exception e) { display.setText("Ошибка"); }
    }

    @FXML protected void onFactClick() {
        try {
            int val = Integer.parseInt(display.getText());
            display.setText(String.valueOf(service.factorial(val)));
            start = true;
        } catch (Exception e) { display.setText("Ошибка"); }
    }

    @FXML protected void onClearClick() { display.clear(); start = true; }

    @FXML protected void onMemClick(ActionEvent event) {
        String type = ((Button)event.getSource()).getText();
        switch (type) {
            case "MS" -> service.memSave(Double.parseDouble(display.getText()));
            case "MR" -> display.setText(String.valueOf(service.memRead()));
            case "M+" -> service.memAdd(Double.parseDouble(display.getText()));
            case "MC" -> service.memClear();
        }
    }
}