package br.ucsal.app.DTO;

public enum OperationsEnum {

    SUM("sum"),
    SUBTRACT("subtract"),
    MULTIPLY("multiply"),
    DIVIDE("divide");

    private String operation;

    OperationsEnum(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

}
