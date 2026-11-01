package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    public TextField display;

    private DamnBigNumber a = DamnBigNumber.DBN_ZERO;
    private DamnBigNumber b = DamnBigNumber.DBN_ZERO;
    private String operation = "+";
    private boolean newNumber = true;

    @FXML
    protected void handleAddClick(ActionEvent actionEvent) {
        setOperation("+");
    }

    @FXML
    protected void handleSubtractClick(ActionEvent actionEvent) {
        setOperation("-");
    }

    @FXML
    protected void handleMultiplyClick(ActionEvent actionEvent) {
        setOperation("*");
    }

    @FXML
    protected void handleDivideClick(ActionEvent actionEvent) {
        setOperation("/");
    }

    @FXML
    protected void handleClearClick(ActionEvent actionEvent) {
        display.setText("");
        a = DamnBigNumber.DBN_ZERO;
        b = DamnBigNumber.DBN_ZERO;
        operation = "+";
        newNumber = true;
    }

    private void setOperation(String op) {
        a = readDisplay();
        operation = op;
        newNumber = true;
    }

    private DamnBigNumber readDisplay() {
        String text = display.getText();
        return new DamnBigNumber(text.isEmpty() ? "0" : text);
    }

    @FXML
    protected void handleEqualsClick(ActionEvent actionEvent){
        b = readDisplay();

        try {
            switch (operation){
                case "+":
                    display.setText(DamnBigNumbers.add(a, b).getNumStr());
                    break;
                case "-":
                    display.setText(DamnBigNumbers.subtract(a, b).getNumStr());
                    break;
                case "*":
                    display.setText(DamnBigNumbers.multiply(a, b).getNumStr());
                    break;
                case "/":
                    display.setText(DamnBigNumbers.divide(a, b).getNumStr());
                    break;
            }
        } catch (Exception e) {
            display.setText("error");
        }
        newNumber = true;
    }

    @FXML
    public void handleDigitClick(ActionEvent actionEvent){
        if (newNumber){
            display.setText("");
            newNumber = false;
        }
        Button source = (Button) actionEvent.getSource();
        display.setText(display.getText()+source.getText());
    }
}
