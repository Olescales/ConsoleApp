package com.innowisegroup.storage.parsers;

import com.innowisegroup.entity.User;
import com.innowisegroup.entity.enums.UserRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserLineParser implements Parser<User> {

    public User parseLineToObject(String line) {
        String[] userData = line.split(";");
        User user = new User();
        user.setId(Long.parseLong(userData[0]));
        user.setName(userData[1]);
        user.setLastName(userData[2]);
        user.setEmail(userData[3]);

        String[] roles = userData[4].split(",");
        List<UserRole> roleList = new ArrayList<>();
        for (String role : roles) {
            roleList.add(UserRole.valueOf(role));
        }
        user.setUserRole(roleList);

        String[] phones = userData[5].split(",");
        List<String> userPhones = new ArrayList<>(Arrays.asList(phones));
        user.setPhoneNumbers(userPhones);
        return user;
    }

    @Override
    public String parseObjectToLine(User user) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(user.getId()).append(";");
        stringBuilder.append(user.getName()).append(";");
        stringBuilder.append(user.getLastName()).append(";");
        stringBuilder.append(user.getEmail()).append(";");
        user.getUserRole().forEach(ur -> stringBuilder.append(ur).append(","));
        stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), ";");
        user.getPhoneNumbers().forEach(p -> stringBuilder.append(p).append(","));
        stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "");
        return stringBuilder.toString();
    }
}