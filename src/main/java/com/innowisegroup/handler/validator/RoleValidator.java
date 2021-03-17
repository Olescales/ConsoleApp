package com.innowisegroup.handler.validator;


import com.innowisegroup.entity.enums.UserRole;

import java.util.Arrays;
import java.util.Map;

public class RoleValidator implements FieldValidator {

    private Map<String, UserRole> rolesMap;
    private static final String PATTERN = "\\d\\s\\d";

    public RoleValidator(Map<String, UserRole> rolesMap) {
        this.rolesMap = rolesMap;
    }

    @Override
    public ValidationResult validateField(String input) {
        int permittedRolePoints = 3;
        if (input.matches(PATTERN)) {
            int actualRolePoints = Arrays.stream(input.split("\\s"))
                    .mapToInt(role -> rolesMap.get(role).getLevel()).sum();
            if (actualRolePoints > permittedRolePoints || (input.length() == 3 && actualRolePoints == 2)) {
                return new ValidationResult(false);
            }
        }
        return new ValidationResult(true);
    }
}