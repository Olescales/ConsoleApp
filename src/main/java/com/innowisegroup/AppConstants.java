package com.innowisegroup;

public class AppConstants {

    public static final String GREETING = "Hello. This is console application which let you create and manage you users.\r\n" +
            "Possible commands:\r\n1 - watch users,\r\n2 - create user,\r\n3 - watch user,\r\n4 - edit user,\r\n5 - delete user.";
    public static final String WRONG_EMAIL = "Wrong email. Email must have characters '@' and '.' like in example -  *****@*****.* ";
    public static final String WRONG_PHONE = "Wrong phone. Phone must looks like - 375** ******* ";
    public static final String CHOOSE_USER_ROLE = "Type in numbers of interesting user roles through whitespace. Example: 'x x'\r\n" +
            " 1 - USER(1 level),\r\n 2 - CUSTOMER(1 level)," +
            "\r\n 3 - ADMIN(2 level),\r\n 4 - PROVIDER(2 level)," +
            "\r\n 5 - SUPER_ADMIN(3 level)).\r\nYou can get only one role from each level or one role from 3 level.";
    public static final String WRONG_INPUT = "Wrong input. Check it and repeat: ";
    public static final String ENTER_PHONE_NUMBER = "Enter phone number as in example '375** *******' : ";
    public static final String PHONES_LEFT = "You can also add %s phones";
    public static final String NO_SUCH_USER_IN_DB = "No such user in DB";
    public static final String FAREWELL = "Good bye!";
    public static final String POSSIBLE_ACTIONS_REMINDER = "Possible commands:\r\n1 - watch users,\r\n2 - register user,\r\n3 - watch user,\r\n4 - edit user,\r\n5 - delete user";
    public static final String ADDED_SUCCESSFULLY = "Added successfully";
    public static final String DELETED_SUCCESSFULLY = "Deleted successfully";
    public static final String ENTER_ID = "Enter id user. You can watch it using command 1";
    public static final String CHOOSE_WHAT_EDIT = "What are you going to edit?\r\n1 - name,\r\n2 - lastName,\r\n3 - email,\r\n4 - roles,\r\n5 - phones.\r\nTo stop editing press C";
    public static final String STOP_INPUT = "To stop input press C";
    public static final String NO_USERS_IN_DB = "There are no users in DB";

}