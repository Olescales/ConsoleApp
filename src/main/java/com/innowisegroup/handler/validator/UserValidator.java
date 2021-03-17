package com.innowisegroup.handler.validator;

import com.innowisegroup.entity.enums.Field;

import java.util.Map;

public class UserValidator implements Validator {

    private final Map<Field, FieldValidator> validators;

    public UserValidator(Map<Field, FieldValidator> validators) {
        this.validators = validators;
    }

    public ValidationResult validate(Field field, String input) {
        FieldValidator fieldValidator = validators.get(field);
        return fieldValidator.validateField(input);
    }
}
