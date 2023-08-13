package com.dseagull.taskmanagement.security.controller;

import com.dseagull.taskmanagement.security.usecase.AuthUseCase;
import com.dseagull.taskmanagement.security.usecase.RegisterUserUseCase;
import com.dseagull.taskmanagement.user.dto.AuthenticateInputDto;
import com.dseagull.taskmanagement.user.dto.AuthenticationOutputDto;
import com.dseagull.taskmanagement.user.dto.ProfileInputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthenticationController implements AuthenticationSwagger {

    private final AuthUseCase authUseCase;
    private final RegisterUserUseCase registerUserUseCase;

    @Override
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationOutputDto> authenticate(AuthenticateInputDto request) {
        return ResponseEntity.ok(authUseCase.authenticate(request));
    }

    @Override
    @PostMapping("/register/{email}")
    public ResponseEntity<AuthenticationOutputDto> finishProfile(String email, ProfileInputDto profile) {
        return ResponseEntity.ok(registerUserUseCase.register(email, profile));
    }
}
