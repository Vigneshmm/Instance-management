package com.freshworks.SpringProject.Instances;

public class OperationDto {

    private String operation;

    public OperationDto() {
    }

    public OperationDto(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
