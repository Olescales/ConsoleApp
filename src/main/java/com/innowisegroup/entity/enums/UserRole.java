package com.innowisegroup.entity.enums;

public enum UserRole {

    USER(1),
    CUSTOMER(1),
    ADMIN(2),
    PROVIDER(2),
    SUPER_ADMIN(3);

    private int level;

    UserRole(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
