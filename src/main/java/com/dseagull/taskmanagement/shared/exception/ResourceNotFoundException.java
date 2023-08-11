package com.dseagull.taskmanagement.shared.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException {
    public ResourceNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}
