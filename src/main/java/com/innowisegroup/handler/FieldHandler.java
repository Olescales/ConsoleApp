package com.innowisegroup.handler;

import com.innowisegroup.handler.validator.Validator;

import java.util.Scanner;

public abstract class FieldHandler<T> {

    protected Scanner scanner;
    protected FieldHandler<T> nextHandler;
    protected Validator fieldValidator;

    public FieldHandler() {
    }

    public FieldHandler(FieldHandler<T> nextHandler) {
        this.nextHandler = nextHandler;
    }

    public FieldHandler(Scanner scanner, FieldHandler<T> nextHandler, Validator fieldValidator) {
        this.scanner = scanner;
        this.nextHandler = nextHandler;
        this.fieldValidator = fieldValidator;
    }

    public FieldHandler(Scanner scanner, Validator fieldValidator) {
        this.scanner = scanner;
        this.fieldValidator = fieldValidator;
    }

    public abstract T handleInput(T entity);
}
