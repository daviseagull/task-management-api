package com.dseagull.taskmanagement.domain.user.service;

import com.dseagull.taskmanagement.domain.user.User;
import com.dseagull.taskmanagement.domain.user.dto.AuthenticateInputDto;
import com.dseagull.taskmanagement.domain.user.dto.AuthenticationOutputDto;
import com.dseagull.taskmanagement.domain.user.dto.RegisterInputDto;
import com.dseagull.taskmanagement.domain.user.model.Status;
import com.dseagull.taskmanagement.domain.user.repository.UserRepository;
import com.dseagull.taskmanagement.security.service.JwtService;
import com.dseagull.taskmanagement.domain.user.exception.UserAlreadyExistsException;
import com.dseagull.taskmanagement.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.lang.Boolean.TRUE;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private AuthenticationOutputDto generateResponse(UserDetails user) {
        String token = jwtService.generateToken(user);
        return AuthenticationOutputDto.builder()
                .token(token)
                .build();
    }

    public AuthenticationOutputDto register(RegisterInputDto request) {
        checkIfIsValid(request);
        User user = createUser(request);
        repository.save(user);
        log.info("User with username created successfully: {}", request.username());
        return generateResponse(user);
    }

    private void checkIfIsValid(RegisterInputDto request) {
        if (repository.existsByUsername(request.username())) {
            String message = "Account with username already exists: " + request.username();
            log.error(message);
            throw new UserAlreadyExistsException(message, HttpStatus.NOT_ACCEPTABLE);
        }

        if (repository.existsByEmail(request.email())) {
            String message = "Account with email already exists: " + request.email();
            log.error(message);
            throw new UserAlreadyExistsException(message, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    private User createUser(RegisterInputDto request) {
        return User.builder()
                .name(request.name())
                .email(request.email())
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .role(request.role())
                .status(Status.builder().enabled(TRUE).build())
                .build();
    }

    public AuthenticationOutputDto authenticate(AuthenticateInputDto request) {
        UserDetails userDetails = repository.findByUsername(request.username())
                .orElseThrow(() -> new UserNotFoundException("User not found: " + request.username(), HttpStatus.NOT_FOUND));

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        );

        authenticationManager.authenticate(token);

        return generateResponse(userDetails);
    }
}
