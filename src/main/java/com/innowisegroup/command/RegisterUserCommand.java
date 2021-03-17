package com.innowisegroup.command;

import com.innowisegroup.AppConstants;
import com.innowisegroup.entity.User;
import com.innowisegroup.handler.FieldHandler;
import com.innowisegroup.service.IUserService;

public class RegisterUserCommand implements Command {

    private IUserService iUserService;
    private FieldHandler<User> startHandler;

    public RegisterUserCommand(IUserService iUserService, FieldHandler<User> startHandler) {
        this.iUserService = iUserService;
        this.startHandler = startHandler;
    }

    @Override
    public String execute() {
        User user = new User();
        User result = startHandler.handleInput(user);
        iUserService.add(result);
        return AppConstants.ADDED_SUCCESSFULLY;
    }
}