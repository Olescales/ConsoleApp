package com.innowisegroup.handler.validator;

public class EmailValidator implements FieldValidator {

    private static final String EMAIL_PATTERN = "^.+@.+\\.[A-z]+$";

    public ValidationResult validateField(String input) {
        if (input.matches(EMAIL_PATTERN)) {
            return new ValidationResult(true);
        }
        return new ValidationResult(false);
    }
}
