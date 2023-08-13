package com.dseagull.taskmanagement.user.exception;

import com.dseagull.taskmanagement.shared.exception.ApiException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ApiException {
    public UserNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}