package com.innowisegroup.handler.validator;

import com.innowisegroup.entity.enums.Field;

public interface Validator {

    ValidationResult validate(Field field, String input);
}
