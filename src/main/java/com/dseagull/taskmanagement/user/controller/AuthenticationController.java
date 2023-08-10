package com.dseagull.taskmanagement.user.controller;

import com.dseagull.taskmanagement.user.controller.swagger.AuthenticationSwagger;
import com.dseagull.taskmanagement.user.dto.AuthenticateInputDto;
import com.dseagull.taskmanagement.user.dto.AuthenticationOutputDto;
import com.dseagull.taskmanagement.user.dto.RegisterInputDto;
import com.dseagull.taskmanagement.user.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationSwagger {

    private final AuthenticationService service;

    @Override
    @PostMapping("/register")
    public ResponseEntity<AuthenticationOutputDto> register(RegisterInputDto request) {
        return ResponseEntity.ok(service.register(request));
    }

    @Override
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationOutputDto> authenticate(AuthenticateInputDto request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

}
