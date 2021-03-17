package com.innowisegroup.handler;

import com.innowisegroup.entity.User;
import com.innowisegroup.handler.validator.Validator;

import java.util.Scanner;

public class InitHandler extends FieldHandler<User> {

    public InitHandler(Scanner scanner, FieldHandler<User> nextHandler, Validator userValidator) {
        super(scanner, nextHandler, userValidator);
    }

    public User handleInput(User entity) {
        return nextHandler.handleInput(entity);
    }
}
