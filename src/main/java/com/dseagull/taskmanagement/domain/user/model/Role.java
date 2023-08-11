package com.dseagull.taskmanagement.domain.user.model;

import com.dseagull.taskmanagement.shared.exception.InvalidEnumValueException;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.http.HttpStatus;

public enum Role {
    APP_ADMIN,
    PROJECT_ADMIN,
    MEMBER;

    @JsonCreator
    public static Role fromString(String value) {
        for (Role role : Role.values()) {
            if (role.name().equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new InvalidEnumValueException("Invalid role", HttpStatus.BAD_REQUEST);
    }
}
