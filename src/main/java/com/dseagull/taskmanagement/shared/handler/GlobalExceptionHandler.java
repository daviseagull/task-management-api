package com.dseagull.taskmanagement.shared.handler;

import com.dseagull.taskmanagement.shared.dto.ErrorOutputDto;
import com.dseagull.taskmanagement.shared.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static ErrorOutputDto buildExceptionOutputDto(RuntimeException exception, HttpStatus status) {
        return ErrorOutputDto.builder()
                .timestamp(LocalDateTime.now().toString())
                .message(exception.getMessage())
                .status(status.value())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorOutputDto> handleException(ApiException exception) {
        return ResponseEntity.status(exception.getStatus()).body(buildExceptionOutputDto(exception, exception.getStatus()));
    }

}
