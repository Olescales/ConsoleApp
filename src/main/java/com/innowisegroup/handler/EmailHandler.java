package com.innowisegroup.handler;

import com.innowisegroup.AppConstants;
import com.innowisegroup.entity.User;
import com.innowisegroup.entity.enums.Field;
import com.innowisegroup.handler.validator.ValidationResult;
import com.innowisegroup.handler.validator.Validator;

import java.util.Scanner;

public class EmailHandler extends FieldHandler<User> {

    public EmailHandler(Scanner scanner, Validator fieldValidator) {
        super(scanner, fieldValidator);
    }

    public EmailHandler(Scanner scanner, FieldHandler<User> nextHandler, Validator fieldValidator) {
        super(scanner, nextHandler, fieldValidator);
    }

    public User handleInput(User entity) {
        while (true) {
            System.out.println("Enter user's email: ");
            String email = scanner.nextLine();
            ValidationResult result = fieldValidator.validate(Field.EMAIL, email);
            if (result.isFieldValidated()) {
                entity.setEmail(email);
                if (nextHandler != null) {
                    return nextHandler.handleInput(entity);
                }
                return entity;
            } else {
                System.out.println(AppConstants.WRONG_EMAIL);
            }
        }
    }
}
