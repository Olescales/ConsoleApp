package com.innowisegroup.handler.validator;

public class PhoneValidator implements FieldValidator {

    private static final String PHONE_PATTERN = "375\\d\\d\\s[\\d]{7}";

    @Override
    public ValidationResult validateField(String input) {
        if (input.matches(PHONE_PATTERN)) {
            return new ValidationResult(true);
        }
        return new ValidationResult(false);
    }
}
