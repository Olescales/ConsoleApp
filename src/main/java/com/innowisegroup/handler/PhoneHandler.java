package com.innowisegroup.handler;

import com.innowisegroup.AppConstants;
import com.innowisegroup.entity.User;
import com.innowisegroup.entity.enums.Field;
import com.innowisegroup.handler.validator.ValidationResult;
import com.innowisegroup.handler.validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneHandler extends FieldHandler<User> {

    public PhoneHandler() {
    }

    public PhoneHandler(Scanner scanner, Validator fieldValidator) {
        super(scanner, fieldValidator);
    }

    public User handleInput(User entity) {
        List<String> phoneNumbers = new ArrayList<>();
        int allowedNumberOfPhones = 3;
        while (allowedNumberOfPhones != 0) {
            System.out.println(AppConstants.ENTER_PHONE_NUMBER);
            String input = scanner.nextLine();
            if (!phoneNumbers.isEmpty() && input.equalsIgnoreCase("C")) {
                break;
            }
            ValidationResult result = fieldValidator.validate(Field.PHONE, input);
            if (result.isFieldValidated()) {
                phoneNumbers.add(input);
                allowedNumberOfPhones--;
                System.out.println(String.format(AppConstants.PHONES_LEFT, allowedNumberOfPhones));
                System.out.println(AppConstants.STOP_INPUT);
            } else {
                System.out.println(AppConstants.WRONG_PHONE);
            }
        }

        if (nextHandler != null) {
            return nextHandler.handleInput(entity);
        } else {
            entity.setPhoneNumbers(phoneNumbers);
            return entity;
        }
    }
}