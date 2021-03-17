package com.innowisegroup.command;

import com.innowisegroup.AppConstants;
import com.innowisegroup.exceptions.NoSuchUserException;
import com.innowisegroup.service.IUserService;

import java.util.Scanner;

public class DeleteUserCommand implements Command {

    private IUserService iUserService;

    public DeleteUserCommand(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @Override
    public String execute() {
        System.out.println(AppConstants.ENTER_ID);
        Scanner sc = new Scanner(System.in);
        long userID = Long.parseLong(sc.nextLine());
        try {
            iUserService.delete(userID);
            return AppConstants.DELETED_SUCCESSFULLY;
        } catch (NoSuchUserException e) {
            return AppConstants.NO_SUCH_USER_IN_DB;
        }
    }
}
