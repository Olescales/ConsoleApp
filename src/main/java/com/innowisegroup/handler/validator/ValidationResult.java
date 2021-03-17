package com.innowisegroup.handler.validator;

public class ValidationResult {

    private boolean fieldValidated;
    private String wrongData;
    private String message;

    public ValidationResult() {
    }

    public ValidationResult(boolean fieldValidated) {
        this.fieldValidated = fieldValidated;
    }

    public ValidationResult(boolean fieldValidated, String message) {
        this.fieldValidated = fieldValidated;
        this.message = message;
    }

    public boolean isFieldValidated() {
        return fieldValidated;
    }

    public void setFieldValidated(boolean fieldValidated) {
        this.fieldValidated = fieldValidated;
    }

    public String getWrongData() {
        return wrongData;
    }

    public void setWrongData(String wrongData) {
        this.wrongData = wrongData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
