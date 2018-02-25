package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import sun.misc.Regexp;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    @FXML
    TextArea textArea;

    public void initialize(){
        textArea.setEditable(false);

    }

    ArrayList pressedNumbers = new ArrayList();
    ToCalc number1 = new ToCalc(0.0, "nvt");
    boolean number1Set = false;
    ToCalc number2 = new ToCalc(0.0, "nvt");

    @FXML
    private void numberPressed(ActionEvent event) {
        Button button = (Button) event.getSource();

        if (button.getId().equals("digit")) {
            pressedNumbers.add(button.getText());
            textArea.setText(pressedNumbersString());
        } else if (button.getId().equals("operation")) {
            if (number1.getNumber() == 0.0) {
                number1.setNumber(Double.parseDouble(pressedNumbersString()));
                pressedNumbers.clear();
                number1.setOperation(button.getText());
            } else if(number1.getOperation().equals("nvt")){
                number1.setOperation(button.getText());
            } else {

                number2.setNumber(Double.parseDouble(pressedNumbersString()));
                pressedNumbers.clear();

                switch (number1.getOperation()) {

                    case "-":
                        number1 = new ToCalc((number1.getNumber() - number2.getNumber()), "nvt");
                        textArea.setText(number1.getNumber() + "");
                        break;
                    case "+":
                        number1 = new ToCalc((number1.getNumber() + number2.getNumber()), "nvt");
                        textArea.setText(number1.getNumber() + "");
                        break;
                    case ":":
                        number1 = new ToCalc((number1.getNumber() / number2.getNumber()), "nvt");
                        textArea.setText(number1.getNumber() + "");
                        break;
                    case "x":
                        number1 = new ToCalc((number1.getNumber() * number2.getNumber()), "nvt");
                        textArea.setText(number1.getNumber() + "");
                        break;
                    case "nvt":
                        System.out.println("nvt Error");
                        break;
                }
            }

            textArea.setText(number1.getNumber() + "");
        }
        if (button.getText().equals("enter")){
            textArea.setText(Double.toString(switchForEnter(number1.getOperation())));
            number2 = new ToCalc(0.0, "nvt");
            number1 = new ToCalc(switchForEnter(number1.getOperation()), "nvt");
            pressedNumbers.clear();
        }

        if (button.getText().equals("Clear")){
            textArea.setText("0");
            number2 = new ToCalc(0.0, "nvt");
            number1 = new ToCalc(0.0, "nvt");
            pressedNumbers.clear();
        }
    }

    private String pressedNumbersString(){
        StringBuilder sb = new StringBuilder();
        for(Object number : pressedNumbers){
            sb.append(number);
        }
        return sb.toString();
    }

    private double switchForEnter(String operation ) {
        switch(operation) {
            case "-":
                return number1.getNumber() - Double.parseDouble(pressedNumbersString());
            case "+":
                return number1.getNumber() + Double.parseDouble(pressedNumbersString());
            case ":":
                return number1.getNumber() / Double.parseDouble(pressedNumbersString());
            case "x":
                return number1.getNumber() * Double.parseDouble(pressedNumbersString());
            case "nvt":
                System.out.println("nvt Error");
                return 0.0;
        }
        return 0.0;
    }

}