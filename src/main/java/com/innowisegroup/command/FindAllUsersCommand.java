package com.innowisegroup.command;

import com.innowisegroup.AppConstants;
import com.innowisegroup.entity.User;
import com.innowisegroup.service.IUserService;

import java.util.List;

public class FindAllUsersCommand implements Command {

    private IUserService iUserService;

    public FindAllUsersCommand(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @Override
    public String execute() {
        List<User> all = iUserService.findAll();
        if (all.isEmpty()) {
            return AppConstants.NO_USERS_IN_DB;
        }
        StringBuilder stringBuilder = new StringBuilder();
        all.forEach(user -> stringBuilder.append(user.toString()).append("\r\n"));
        return stringBuilder.toString();
    }
}
