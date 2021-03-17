package com.innowisegroup.handler;

import com.innowisegroup.AppConstants;
import com.innowisegroup.entity.User;
import com.innowisegroup.entity.enums.Field;
import com.innowisegroup.handler.validator.ValidationResult;
import com.innowisegroup.handler.validator.Validator;

import java.util.Scanner;

public class LastNameHandler extends FieldHandler<User> {

    public LastNameHandler(Scanner scanner, Validator fieldValidator) {
        super(scanner, fieldValidator);
    }

    public LastNameHandler(Scanner scanner, FieldHandler<User> nextHandler, Validator fieldValidator) {
        super(scanner, nextHandler, fieldValidator);
    }

    public User handleInput(User entity) {
        while (true) {
            System.out.println("Enter user lastName: ");
            String lastName = scanner.nextLine();
            ValidationResult validate = fieldValidator.validate(Field.LASTNAME, lastName);
            if (validate.isFieldValidated()) {
                entity.setLastName(lastName);
                if (nextHandler != null) {
                    return nextHandler.handleInput(entity);
                } else {
                    return entity;
                }
            }
            System.out.println(AppConstants.WRONG_INPUT);
        }
    }
}
