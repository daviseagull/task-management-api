package com.dseagull.taskmanagement.user.util;

import com.dseagull.taskmanagement.shared.exception.ResourceNotFoundException;
import com.dseagull.taskmanagement.user.User;
import com.dseagull.taskmanagement.user.dto.UserFilters;
import com.dseagull.taskmanagement.user.model.Role;
import com.dseagull.taskmanagement.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class UserUtils {

    private final UserRepository repository;

    private static User getUser(Optional<User> user, String message) {
        if (user.isEmpty()) {
            throw new ResourceNotFoundException(message, HttpStatus.NOT_FOUND);
        }
        return user.get();
    }

    public static UserFilters getFilters(String username, String email, String role) {
        return UserFilters.builder()
                .username(username)
                .email(email)
                .role(StringUtils.isBlank(role) ? Optional.empty() : Optional.of(Role.valueOf(role)))
                .build();
    }

    public User findUserById(String id) {
        Optional<User> user = repository.findById(id);
        return getUser(user, format("User with id not found: %s", id));
    }

    public User findUserByEmail(String email) {
        Optional<User> user = repository.findByEmail(email);
        return getUser(user, format("User with email not found: %s", email));
    }

    public User findUserByUsername(String username) {
        Optional<User> user = repository.findByUsername(username);
        return getUser(user, format("User with username not found: %s", username));
    }
}
