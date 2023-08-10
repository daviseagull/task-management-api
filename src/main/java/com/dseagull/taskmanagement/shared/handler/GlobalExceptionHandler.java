package com.dseagull.taskmanagement.shared.handler;

import com.dseagull.taskmanagement.shared.dto.ErrorOutputDto;
import com.dseagull.taskmanagement.shared.exception.BusinessLogicException;
import com.dseagull.taskmanagement.shared.exception.ResourceNotFoundException;
import com.dseagull.taskmanagement.user.exception.UserAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private static ErrorOutputDto buildExceptionOutputDto(RuntimeException exception, HttpStatus status) {
        return ErrorOutputDto.builder()
                .timestamp(LocalDateTime.now().toString())
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
        log.error(exception.getMessage());
        return ResponseEntity.badRequest().body(buildExceptionOutputDto(exception, HttpStatus.BAD_REQUEST));
    }


    @ResponseStatus(
            value = HttpStatus.BAD_REQUEST,
            reason = "Username not found"
    )
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorOutputDto> handleUsernameNotFound(UsernameNotFoundException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.badRequest().body(buildExceptionOutputDto(exception, HttpStatus.BAD_REQUEST));
    }

    @ResponseStatus(
            value = HttpStatus.BAD_REQUEST,
            reason = "Resource not found"
    )
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorOutputDto> handleResourceNotFound(ResourceNotFoundException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.badRequest().body(buildExceptionOutputDto(exception, HttpStatus.BAD_REQUEST));
    }

    @ResponseStatus(
            value = HttpStatus.BAD_REQUEST,
            reason = "Request not compatible to app business logic"
    )
    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<ErrorOutputDto> handleBusinessLogic(BusinessLogicException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.badRequest().body(buildExceptionOutputDto(exception, HttpStatus.BAD_REQUEST));
    }

}
