package com.innowisegroup.handler.validator;

public class LastNameValidator implements FieldValidator {

    @Override
    public ValidationResult validateField(String input) {
        if (input.length() > 1 && input.length() < 20){
            return new ValidationResult(true);
        }
        return new ValidationResult(false);
    }
}
