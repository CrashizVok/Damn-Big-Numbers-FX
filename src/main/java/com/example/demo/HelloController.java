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
    @FXML
    protected void handleAddClick(ActionEvent actionEvent) {
        String displayNumSrt = display.getText();
        a = new DamnBigNumber(displayNumSrt);
        operation = "+";
    }

    @FXML
    protected void handleEqualsClick(ActionEvent actionEvent){
        String displayNumSrt = display.getText();
        b = new DamnBigNumber(displayNumSrt);

        switch (operation){
            case "+":
                DamnBigNumber c = DamnBigNumbers.add(a, b);
                display.setText(c.getNumStr());
                break;
            case "-":
                DamnBigNumber d = DamnBigNumbers.subtract(a, b);
                display.setText(d.getNumStr());
                break;
            case "*":
                DamnBigNumber f = DamnBigNumbers.multiply(a, b);
                display.setText(f.getNumStr());
                break;
            case "/":
                DamnBigNumber g = DamnBigNumbers.divide(a, b);
                display.setText(g.getNumStr());
                break;
        }
    }
    public void handleDigitClick(ActionEvent actionEvent){
        Button source = (Button) actionEvent.getSource();
        String value = source.getText();
        int digit = Integer.parseInt(value);

        display.setText(display.getText()+value);
    }
}