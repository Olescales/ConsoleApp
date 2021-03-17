package com.innowisegroup.handler;

import com.innowisegroup.AppConstants;
import com.innowisegroup.entity.User;
import com.innowisegroup.entity.enums.Field;
import com.innowisegroup.entity.enums.UserRole;
import com.innowisegroup.handler.validator.ValidationResult;
import com.innowisegroup.handler.validator.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserRoleHandler extends FieldHandler<User> {

    private Map<String, UserRole> rolesMap;

    public UserRoleHandler(Scanner scanner, Validator fieldValidator, Map<String, UserRole> rolesMap) {
        super(scanner, fieldValidator);
        this.rolesMap = rolesMap;
    }

    public UserRoleHandler(Scanner scanner, FieldHandler<User> nextHandler, Validator fieldValidator, Map<String, UserRole> rolesMap) {
        super(scanner, nextHandler, fieldValidator);
        this.rolesMap = rolesMap;
    }

    public User handleInput(User entity) {
        while (true) {
            System.out.println(AppConstants.CHOOSE_USER_ROLE);
            String userInput = scanner.nextLine();
            ValidationResult result = fieldValidator.validate(Field.ROLE, userInput);
            if (result.isFieldValidated()) {
                List<UserRole> roleList = Arrays.stream(userInput.split("\\s"))
                        .map(role -> rolesMap.get(role))
                        .collect(Collectors.toList());
                entity.setUserRole(roleList);
                if (nextHandler != null) {
                    return nextHandler.handleInput(entity);
                }
                return entity;
            } else {
                System.out.println(AppConstants.WRONG_INPUT);
            }
        }
    }
}
