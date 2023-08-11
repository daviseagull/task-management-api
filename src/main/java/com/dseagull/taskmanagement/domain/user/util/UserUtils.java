package com.dseagull.taskmanagement.domain.user.util;

import com.dseagull.taskmanagement.domain.user.dto.UserFilters;
import com.dseagull.taskmanagement.domain.user.model.Role;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public class UserUtils {

    public static UserFilters getFilters(String username, String email, String role) {
        return UserFilters.builder()
                .username(username)
                .email(email)
                .role(StringUtils.isBlank(role) ? Optional.empty() : Optional.of(Role.valueOf(role)))
                .build();
    }
}
