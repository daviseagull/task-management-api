package com.dseagull.taskmanagement.shared.exception;

import org.springframework.http.HttpStatus;

public class InvalidEnumValueException extends ApiException {

    public InvalidEnumValueException(String message, HttpStatus status) {
        super(message, status);
    }
}
