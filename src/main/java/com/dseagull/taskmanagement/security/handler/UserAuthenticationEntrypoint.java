package com.dseagull.taskmanagement.security.handler;

import com.dseagull.taskmanagement.shared.dto.ErrorOutputDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Component
public class UserAuthenticationEntrypoint implements AuthenticationEntryPoint {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        log.error("Authentication error: {}", authException.getMessage(), authException);
        ErrorOutputDto error = ErrorOutputDto.builder()
                .timestamp(LocalDateTime.now().toString())
                .message("Unknown authentication error")
                .status(HttpStatus.UNAUTHORIZED.value())
                .build();
        OBJECT_MAPPER.writeValue(response.getOutputStream(), error);
    }
}
