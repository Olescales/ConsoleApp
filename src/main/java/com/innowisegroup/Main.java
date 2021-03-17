package com.innowisegroup;

import com.innowisegroup.command.Command;
import com.innowisegroup.command.CommandFactory;
import com.innowisegroup.command.DeleteUserCommand;
import com.innowisegroup.command.EditUserCommand;
import com.innowisegroup.command.FindAllUsersCommand;
import com.innowisegroup.command.GetUserCommand;
import com.innowisegroup.command.RegisterUserCommand;
import com.innowisegroup.entity.User;
import com.innowisegroup.entity.enums.UserRole;
import com.innowisegroup.entity.enums.Field;
import com.innowisegroup.handler.EmailHandler;
import com.innowisegroup.handler.FieldHandler;
import com.innowisegroup.handler.InitHandler;
import com.innowisegroup.handler.LastNameHandler;
import com.innowisegroup.handler.NameHandler;
import com.innowisegroup.handler.PhoneHandler;
import com.innowisegroup.handler.UserRoleHandler;
import com.innowisegroup.handler.validator.LastNameValidator;
import com.innowisegroup.handler.validator.NameValidator;
import com.innowisegroup.service.IUserService;
import com.innowisegroup.service.UserService;
import com.innowisegroup.storage.DatabaseProvider;
import com.innowisegroup.storage.FileProvider;
import com.innowisegroup.storage.parsers.Parser;
import com.innowisegroup.storage.parsers.UserLineParser;
import com.innowisegroup.handler.validator.EmailValidator;
import com.innowisegroup.handler.validator.FieldValidator;
import com.innowisegroup.handler.validator.PhoneValidator;
import com.innowisegroup.handler.validator.RoleValidator;
import com.innowisegroup.handler.validator.UserValidator;
import com.innowisegroup.handler.validator.Validator;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Map<String, UserRole> rolesMap = new HashMap<>();
        rolesMap.put("1", UserRole.USER);
        rolesMap.put("2", UserRole.CUSTOMER);
        rolesMap.put("3", UserRole.ADMIN);
        rolesMap.put("4", UserRole.PROVIDER);
        rolesMap.put("5", UserRole.SUPER_ADMIN);

        NameValidator nameValidator = new NameValidator();
        LastNameValidator lastNameValidator = new LastNameValidator();
        EmailValidator emailValidator = new EmailValidator();
        RoleValidator roleValidator = new RoleValidator(rolesMap);
        PhoneValidator phoneValidator = new PhoneValidator();

        Map<Field, FieldValidator> validators = new EnumMap<>(Field.class);
        validators.put(Field.NAME, nameValidator);
        validators.put(Field.LASTNAME, lastNameValidator);
        validators.put(Field.EMAIL, emailValidator);
        validators.put(Field.ROLE, roleValidator);
        validators.put(Field.PHONE, phoneValidator);

        Validator userValidator = new UserValidator(validators);
        FieldHandler<User> phoneHandler = new PhoneHandler(sc, userValidator);
        FieldHandler<User> userRoleHandler = new UserRoleHandler(sc, phoneHandler, userValidator, rolesMap);
        FieldHandler<User> emailHandler = new EmailHandler(sc, userRoleHandler, userValidator);
        FieldHandler<User> lastNameHandler = new LastNameHandler(sc, emailHandler, userValidator);
        FieldHandler<User> nameHandler = new NameHandler(sc, lastNameHandler, userValidator);
        FieldHandler<User> startHandler = new InitHandler(sc, nameHandler, userValidator);

        FieldHandler<User> editPhoneHandler = new PhoneHandler(sc, userValidator);
        FieldHandler<User> editUserRoleHandler = new UserRoleHandler(sc, userValidator, rolesMap);
        FieldHandler<User> editEmailHandler = new EmailHandler(sc, userValidator);
        FieldHandler<User> editLastNameHandler = new LastNameHandler(sc, userValidator);
        FieldHandler<User> editNameHandler = new NameHandler(sc, userValidator);

        Map<String, FieldHandler<User>> editHandlers = new HashMap<>();
        editHandlers.put("1", editNameHandler);
        editHandlers.put("2", editLastNameHandler);
        editHandlers.put("3", editEmailHandler);
        editHandlers.put("4", editUserRoleHandler);
        editHandlers.put("5", editPhoneHandler);

        String defaultFilePath = "E:\\Oleg\\Програм\\storage.docx";
        Parser<User> userParser = new UserLineParser();
        DatabaseProvider<User> databaseProvider = new FileProvider(userParser, defaultFilePath);
        IUserService iUserService = new UserService(databaseProvider);

        Command registerUserCommand = new RegisterUserCommand(iUserService, startHandler);
        Command deleteUserCommand = new DeleteUserCommand(iUserService);
        Command editUserCommand = new EditUserCommand(iUserService, editHandlers);
        Command getUserCommand = new GetUserCommand(iUserService);
        Command findAllUsersCommand = new FindAllUsersCommand(iUserService);

        Map<String, Command> commands = new HashMap<>();
        commands.put("1", findAllUsersCommand);
        commands.put("2", registerUserCommand);
        commands.put("3", getUserCommand);
        commands.put("4", editUserCommand);
        commands.put("5", deleteUserCommand);

        CommandFactory commandFactory = new CommandFactory(commands);


        System.out.println(AppConstants.GREETING);
        String action = "";
        while (!action.equalsIgnoreCase("quit")) {
            action = sc.nextLine();
            Command command = commandFactory.getCommand(action);
            String answer = command.execute();
            if (answer != null) {
                System.out.println(answer);
            }
            System.out.println(AppConstants.POSSIBLE_ACTIONS_REMINDER);
        }
        System.out.println(AppConstants.FAREWELL);
    }
}