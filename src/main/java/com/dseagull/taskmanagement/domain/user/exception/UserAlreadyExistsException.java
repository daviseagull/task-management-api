package com.dseagull.taskmanagement.domain.user.exception;

import com.dseagull.taskmanagement.shared.exception.ApiException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends ApiException {
    public UserAlreadyExistsException(String message, HttpStatus status) {
        super(message, status);
    }
}
