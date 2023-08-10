package com.dseagull.taskmanagement.shared.handler;

import com.dseagull.taskmanagement.shared.dto.ErrorOutputDto;
import com.dseagull.taskmanagement.user.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static ErrorOutputDto buildExceptionOutputDto(RuntimeException exception, HttpStatus status) {
        return ErrorOutputDto.builder()
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .status(status.value())
                .build();
    }

    @ResponseStatus(
            value = HttpStatus.BAD_REQUEST,
            reason = "User already exists"
    )
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorOutputDto> handleUserAlreadyExists(UserAlreadyExistsException exception) {
        return ResponseEntity.badRequest().body(buildExceptionOutputDto(exception, HttpStatus.BAD_REQUEST));
    }


    @ResponseStatus(
            value = HttpStatus.BAD_REQUEST,
            reason = "Username not found"
    )
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorOutputDto> handleUsernameNotFound(UsernameNotFoundException exception) {
        return ResponseEntity.badRequest().body(buildExceptionOutputDto(exception, HttpStatus.BAD_REQUEST));
    }

}
