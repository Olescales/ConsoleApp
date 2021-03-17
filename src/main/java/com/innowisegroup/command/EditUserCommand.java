package com.innowisegroup.command;

import com.innowisegroup.AppConstants;
import com.innowisegroup.entity.User;
import com.innowisegroup.exceptions.NoSuchUserException;
import com.innowisegroup.handler.FieldHandler;
import com.innowisegroup.service.IUserService;

import java.util.Map;
import java.util.Scanner;

public class EditUserCommand implements Command {

    private IUserService iUserService;
    private Map<String, FieldHandler<User>> editHandlers;

    public EditUserCommand(IUserService iUserService, Map<String, FieldHandler<User>> editHandlers) {
        this.iUserService = iUserService;
        this.editHandlers = editHandlers;
    }

    @Override
    public String execute() {
        System.out.println(AppConstants.ENTER_ID);
        Scanner scanner = new Scanner(System.in);
        long userID = Long.parseLong(scanner.nextLine());
        try {
            while (true) {
                User user = iUserService.get(userID);
                System.out.println(AppConstants.CHOOSE_WHAT_EDIT);
                String fieldToEdit = scanner.nextLine();
                if (fieldToEdit.equalsIgnoreCase("C")) {
                    return user.toString();
                }
                FieldHandler<User> userFieldHandler = editHandlers.get(fieldToEdit);
                if (userFieldHandler == null) {
                    System.out.println(AppConstants.WRONG_INPUT);
                } else {
                    userFieldHandler.handleInput(user);
                }
            }
        } catch (NoSuchUserException e) {
            return AppConstants.NO_SUCH_USER_IN_DB;
        }
    }
}
