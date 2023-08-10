package com.dseagull.taskmanagement.user.controller;

import com.dseagull.taskmanagement.user.dto.AuthenticateInputDto;
import com.dseagull.taskmanagement.user.dto.AuthenticationOutputDto;
import com.dseagull.taskmanagement.user.dto.RegisterInputDto;
import com.dseagull.taskmanagement.user.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationOutputDto> register(@RequestBody RegisterInputDto request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationOutputDto> authenticate(@RequestBody AuthenticateInputDto request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

}
