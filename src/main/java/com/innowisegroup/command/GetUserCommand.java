package com.innowisegroup.command;

import com.innowisegroup.AppConstants;
import com.innowisegroup.entity.User;
import com.innowisegroup.exceptions.NoSuchUserException;
import com.innowisegroup.service.IUserService;

import java.util.Scanner;

public class GetUserCommand implements Command {

    private IUserService iUserService;

    public GetUserCommand(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @Override
    public String execute() {
        System.out.println(AppConstants.ENTER_ID);
        Scanner scanner = new Scanner(System.in);
        Long userID = Long.parseLong(scanner.nextLine());
        try {
            User user = iUserService.get(userID);
            return user.toString();
        } catch (NoSuchUserException e) {
            return AppConstants.NO_SUCH_USER_IN_DB;
        }
    }
}
