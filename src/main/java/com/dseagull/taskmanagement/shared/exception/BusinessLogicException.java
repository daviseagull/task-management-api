package com.dseagull.taskmanagement.shared.exception;

import org.springframework.http.HttpStatus;

public class BusinessLogicException extends ApiException {
    public BusinessLogicException(String message, HttpStatus status) {
        super(message, status);
    }
}
