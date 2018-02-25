package sample;

public class ToCalc {
    private double number = 0.0;
    private String operation;

    public ToCalc(double number, String operation) {
        this.number = number;
        this.operation = operation;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}

