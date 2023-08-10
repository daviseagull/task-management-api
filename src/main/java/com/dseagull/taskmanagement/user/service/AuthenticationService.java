package com.dseagull.taskmanagement.user.service;

import com.dseagull.taskmanagement.security.service.JwtService;
import com.dseagull.taskmanagement.user.User;
import com.dseagull.taskmanagement.user.dto.AuthenticateInputDto;
import com.dseagull.taskmanagement.user.dto.AuthenticationOutputDto;
import com.dseagull.taskmanagement.user.dto.RegisterInputDto;
import com.dseagull.taskmanagement.user.exception.UserAlreadyExistsException;
import com.dseagull.taskmanagement.user.model.Status;
import com.dseagull.taskmanagement.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.lang.Boolean.TRUE;

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
        return generateResponse(user);
    }

    private void checkIfIsValid(RegisterInputDto request) {
        if (repository.existsByUsername(request.username())) {
            throw new UserAlreadyExistsException("User with username used already exists: " + request.username());
        }

        if (repository.existsByEmail(request.email())) {
            throw new UserAlreadyExistsException("User with email used already exists: " + request.email());
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
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        UserDetails userDetails = repository.findByUsername(request.username())
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + request.username()));
        return generateResponse(userDetails);
    }
}
